package com.example.pdvapp.service;

import com.example.pdvapp.dto.ProductDTO;
import com.example.pdvapp.dto.ProductInfoDTO;
import com.example.pdvapp.dto.SaleDTO;
import com.example.pdvapp.dto.SaleInfoDTO;
import com.example.pdvapp.entity.ItemSale;
import com.example.pdvapp.entity.Product;
import com.example.pdvapp.entity.Sale;
import com.example.pdvapp.entity.User;
import com.example.pdvapp.repository.ItemSaleRepository;
import com.example.pdvapp.repository.ProductRepository;
import com.example.pdvapp.repository.SaleRepository;
import com.example.pdvapp.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final SaleRepository saleRepository;
    private final ItemSaleRepository itemSaleRepository;

    public SaleService(@Autowired UserRepository userRepository,
                       @Autowired ProductRepository productRepository,
                       @Autowired SaleRepository saleRepository,
                       @Autowired ItemSaleRepository itemSaleRepository){
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.saleRepository = saleRepository;
        this.itemSaleRepository = itemSaleRepository;
    }

    public List<SaleInfoDTO> findAll(){
        return saleRepository.findAll().stream().map(sale -> getSaleInfo(sale)).collect(Collectors.toList());

    }

    private SaleInfoDTO getSaleInfo(Sale sale) {
        SaleInfoDTO saleInfoDTO = new SaleInfoDTO();
        saleInfoDTO.setUser(sale.getUser().getName());
        saleInfoDTO.setData(sale.getSaleDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        saleInfoDTO.setProducts(getProductInfo(sale.getItemSales()));

        return saleInfoDTO;
    }

    private List<ProductInfoDTO> getProductInfo(List<ItemSale> itens) {
        return itens.stream().map(itemSale -> {
            ProductInfoDTO productInfoDTO = new ProductInfoDTO();
            productInfoDTO.setDescription(itemSale.getProduct().getDescription());
            productInfoDTO.setQuantity(itemSale.getQuantity());

            return productInfoDTO;
        }).collect(Collectors.toList());
    }

    @Transactional
    public long save(SaleDTO saleDTO){

        User user = userRepository.findById(saleDTO.getUserId()).orElse(null);

        Sale newSale = new Sale();
        newSale.setUser(user);
        newSale.setSaleDate(LocalDate.now());
        List<ItemSale> itens = getItemSale(saleDTO.getItens());

        newSale = saleRepository.save(newSale);

        saveItemSale(itens, newSale);

        return newSale.getId();
    }

    private void saveItemSale(List<ItemSale> itens, Sale newSale) {
        for(ItemSale item: itens){
            item.setSale(newSale);
            itemSaleRepository.save(item);
        }
    }

    private List<ItemSale> getItemSale(List<ProductDTO> products){
        return products.stream().map(item -> {
            Product product = productRepository.getReferenceById(item.getProductId());

            ItemSale itemSale = new ItemSale();
            itemSale.setProduct(product);
            itemSale.setQuantity(item.getQuantity());

            return itemSale;
        }).collect(Collectors.toList());
    }

    public SaleInfoDTO findById(long id) {
        Sale sale = saleRepository.findById(id).get();
        SaleInfoDTO saleInfoDTO = getSaleInfo(sale);
        return saleInfoDTO;
    }
}
