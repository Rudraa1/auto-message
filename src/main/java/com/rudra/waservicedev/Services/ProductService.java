package com.rudra.waservicedev.Services;


import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import com.rudra.waservicedev.Models.PriceOption;
import com.rudra.waservicedev.Models.Product;
import com.rudra.waservicedev.Models.WebhookResponse;
import com.rudra.waservicedev.Repository.InventoryRepository;
import com.rudra.waservicedev.Repository.PriceOptionRepository;
import com.rudra.waservicedev.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductService implements ProductServiceInterface{

    @Autowired
    private ProductRepository productRepository;

    private InventoryRepository inventoryRepository;

    private final Translate translateService;

    private PriceOptionRepository priceOptionRepository;

    @Value("${google.cloud.translate.api-key}")
    private String apiKey;


    public ProductService(PriceOptionRepository priceOptionRepository, InventoryRepository inventoryRepository){
        this.priceOptionRepository = priceOptionRepository;
        this.inventoryRepository = inventoryRepository;
        this.translateService = TranslateOptions.getDefaultInstance().getService();
    }

    @Override
    public void updatePrices(Map<String, Double> priceUpdates) {
        for (Map.Entry<String, Double> entry : priceUpdates.entrySet()) {
            String productName = entry.getKey();
            Double price = entry.getValue();
            Product product = productRepository.findByProductName(productName);
            if (product != null) {
                product.setPrice(price);
                productRepository.save(product);
            }
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductByName(String productName) {
        return productRepository.findByProductName(productName);
    }

    @Override
    public Product addNewProduct(Product product) {
        inventoryRepository.save(product);

        return productRepository.save(product);
    }

//    @Override
//    public WebhookResponse getPriceOptions(String productName) {
//
//        System.out.println("thid is it " + productName);
//        // Retrieve product from the database
//        Product product = productRepository.findByProductName(productName);
//       if (product == null) {
//            throw new RuntimeException("Product not found");
//        }
//
//        // Get price options from the product
//        List<PriceOption> priceOptions = product.getPriceOptions();
//
//        // Format the price options into a message
//        StringBuilder message = new StringBuilder("Here are the price options for " + productName + ":\n");
//        for (PriceOption option : priceOptions) {
//            message.append(option.getQuantity()).append(" tons: ").append(option.getPrice()).append(" Rs\n");
//        }
//
//        WebhookResponse response = new WebhookResponse();
//        response.setReply_message(message.toString());
////        System.out.println(response);
//
//        return response;
//    }


    @Override
    public WebhookResponse getPriceOptions(String productName) {
        // Check if the product name is in Hindi
        if (isHindi(productName)) {
            // Translate Hindi product name to English
            productName = translateToEnglish(productName, apiKey);
        }

        System.out.println("product " + productName);

        // Now you can proceed with querying the database using the English product name
        // Retrieve product from the database
        Product product = productRepository.findByProductName(productName);
        if (product == null) {
            throw new RuntimeException("Product not found");
        }


        boolean isSpecialCustomer = false;

        // Get price options from the product
        List<PriceOption> priceOptions = product.getPriceOptions();

        // Format the price options into a message
        StringBuilder message = new StringBuilder("Here are the price options for " + productName + ":\n");
        int optionNumber = 1;
        for (PriceOption option : priceOptions) {
            double price = option.getPrice();
            if (isSpecialCustomer) {
                price = applyDiscount(price);
            }
            message.append(optionNumber).append(". ").append(option.getQuantity()).append(" tons: ").append(price).append(" Rs\n");
            optionNumber++;
        }
        message.append("Choose one price option.");

        // Create a webhook response and set the reply message
        WebhookResponse response = new WebhookResponse();
        response.setReply_message(message.toString());

        return response;
    }

    private boolean isHindi(String text) {
        // Implement logic to detect if the text is in Hindi
        // You can use a language detection library or implement your own logic
        // For simplicity, let's assume any text containing Hindi characters is considered Hindi
        return text.matches(".*\\p{InDevanagari}.*");
    }

    private String translateToEnglish(String hindiText, String apiKey) {
        // Create Translate service object using API key
        Translate translate = TranslateOptions.newBuilder().setApiKey(apiKey).build().getService();

        // Translate Hindi text to English
        Translation translation = translate.translate(hindiText, Translate.TranslateOption.sourceLanguage("hi"), Translate.TranslateOption.targetLanguage("en"));

        return translation.getTranslatedText();
    }


    private double applyDiscount(double price) {
        // Apply discount logic here, for example:
        // 10% discount for special customers
        return price * 0.9;
    }

}




