package com.example.smsshop.controller;


import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.smsshop.Config.JwtTokenUtil;
import com.example.smsshop.Config.JwtUserDetailsService;
import com.example.smsshop.Dao.CategoryDao;
import com.example.smsshop.Dao.ProductDao;
import com.example.smsshop.Dao.PurchaseDao;
import com.example.smsshop.Dao.ShippingDao;
import com.example.smsshop.Dao.ShoppingDao;
import com.example.smsshop.Dao.UserDao;
import com.example.smsshop.Entity.Category;
import com.example.smsshop.Entity.JwtRequest;
import com.example.smsshop.Entity.JwtResponse;
import com.example.smsshop.Entity.Product;
import com.example.smsshop.Entity.PurchasedProduct;
import com.example.smsshop.Entity.ShippingDetail;
import com.example.smsshop.Entity.ShoppingCart;
import com.example.smsshop.Entity.User;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private ShoppingDao shoppingDao;

	@Autowired
	private ShippingDao shippingDao;

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PurchaseDao purchaseDao;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	static final Logger log = Logger.getLogger(ProductController.class.getName());

	@PostMapping("/authenticate")
	public JwtResponse authentication(@RequestBody JwtRequest request) {

		try {
			authenticate(request.getUsername(), request.getPassword());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info(e.getMessage());
		}

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(request.getUsername());
		
		final String token = jwtTokenUtil.generateToken(userDetails);

		return new JwtResponse(token);

	}
	
	@PostMapping("/saveSignUpForm")
	public User saveSignUp(@RequestBody User signUpValue) {
		User userEntity = new User();
		userEntity.setUserName(signUpValue.getUserName());
		userEntity.setPassWord(signUpValue.getPassWord());
		userEntity.setRole("0");
		userEntity.setEmail(signUpValue.getEmail());
		return this.userDao.save(userEntity);
	}
	
	@GetMapping("/getUserData/{name}")
	public User getUserData(@PathVariable(value = "name") String name) {
		return this.userDao.getByUserName(name);
	}

	@GetMapping("/category")
	public List<Category> getAll() {
		List<Category> categoryList = new ArrayList<Category>();
		categoryDao.findAll().iterator().forEachRemaining(action -> categoryList.add(action));
		return categoryList;
	}

	@PostMapping("/saveProduct")
	public Product save(@RequestBody Product product) {
		return productDao.save(product);
	}

	@GetMapping("/getAll")
	public List<Product> getProductList(){
		List<Product> productList = new ArrayList<Product>();
		productDao.findAll().forEach(action->{
			productList.add(action);
		});
		return productList;
	}

	@GetMapping("/getId{id}")
	public Product getByProductID(@PathVariable(value="id") int id){
		Optional<Product> idValue = productDao.findById(id);
		return idValue.get();
	}

	@PutMapping("/update{id}")
	public Product update(@PathVariable(value="id") int id, @RequestBody Product product) {
		if(Objects.nonNull(id)) {
			product.setId(id);
		}
		return this.productDao.save(product);
	}

	@DeleteMapping("/delete{id}")
	public void delete(@PathVariable(value="id") int id) {
		this.productDao.deleteById(id);
	}

	@GetMapping("/filteredCategory{category}")
	public List<Product> filterByCategory(@PathVariable(value="category") String value) {
		List<Product> list = this.productDao.getByCategory(value);
		System.out.println(list);
		log.info("List Value" + list);
		return list;

	}
	@PutMapping("/updateQuantity/{productId}/{isAdd}")
	public Product updateQuantity(@PathVariable(value="productId") String productId, @PathVariable(value="isAdd") String isAdd) {
		Product prdUpdate = productDao.findById(Integer.parseInt(productId)).get();
		if(prdUpdate.getQuantityId() == 0) {
			prdUpdate.setQuantityId(1);
		} else {
			if(Boolean.valueOf(isAdd)) {
				prdUpdate.setQuantityId(prdUpdate.getQuantityId() + 1); 
			} else {
				prdUpdate.setQuantityId(prdUpdate.getQuantityId() - 1); 
			}
		}
		return this.productDao.save(prdUpdate);
	}

	@GetMapping("/getQuantity{productId}")
	public ShoppingCart getByQuantity(@PathVariable int productId) {
		return this.shoppingDao.getByProductId(productId);
	}

	@GetMapping("/getProductByQuantity")
	public List<Product> productByQuantity() {
		return this.productDao.getProductByQuantity();
	}

	@PostMapping("/saveShipDetail")
	public ShippingDetail saveShipDetail(@RequestBody ShippingDetail detail) {
		detail.setPlacedDate(Date.valueOf(LocalDate.now()));
		return this.shippingDao.save(detail);
	}

	@GetMapping("/getShipDetail{id}")
	public ShippingDetail getShipDetail(@PathVariable(value="id") int id) {
		return this.shippingDao.findById(id).get();
	}

	@PutMapping("/updateProductList")
	public List<Product> getShipDetail(@RequestBody List<Product> productList) {
		Iterable<Product> productIterate = productList;
		return (List<Product>) this.productDao.saveAll(productIterate);
	}

	@GetMapping("/getAllDetail")
	public List<ShippingDetail> getAllDetail() {
		return (List<ShippingDetail>) this.shippingDao.findAll();
	}
	
	@PostMapping("/savePurchasedData") 
	public List<PurchasedProduct> savePurchasedData(@RequestBody List<PurchasedProduct> productList) {
		return (List<PurchasedProduct>) this.purchaseDao.saveAll(productList);
	}
	
	@GetMapping("/getPurchasedData{shipId}")
	public List<PurchasedProduct> getPurchasedData(@PathVariable(value = "shipId")  int shipId) {
		return (List<PurchasedProduct>) this.purchaseDao.getPurchaseData(shipId);
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
