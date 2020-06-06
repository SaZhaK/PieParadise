package Application.Entities;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private String name;
    private String lastName;
    private String mail;
    private String phone;
    private String address;
    private String cardNumber;
    private String[] purchases;

    List<ProductInfo> productInfoList = new ArrayList<>();
    List<CustomPie> customPieList = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String[] getPurchases() {
        return purchases;
    }

    public void setPurchases(String[] purchases) {
        this.purchases = purchases;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public List<ProductInfo> getProductInfoList() {
        return productInfoList;
    }

    public List<CustomPie> getCustomPieList() {
        return customPieList;
    }

    @Override
    public String toString() {
        return "Order name: " + name +
                ", lastName: " + lastName +
                ", address: " + address +
                ", mail: " + mail +
                ", phone number: " + phone +
                ", cardNumber: " + cardNumber;
    }

    public void parse() {
        for (String purchase : purchases) {
            if (purchase.contains("/")) {
                CustomPie customPie = new CustomPie();
                String[] elements = purchase.split("/");

                for (String element : elements) {
                    String[] innerElements = element.split(" ");

                    StringBuilder name = new StringBuilder();

                    for (int j = 0; j < innerElements.length - 1; j++) {
                        name.append(innerElements[j]).append(" ");
                    }

                    customPie.getIngredients().add(name.toString().trim());
                }

                customPieList.add(customPie);
            } else {
                String[] elements = purchase.split(" ");

                String id = elements[0];
                StringBuilder productName = new StringBuilder();
                String price = elements[elements.length - 2];
                String amount = elements[elements.length - 1];

                for (int i = 1; i < elements.length - 2; i++) {
                    productName.append(elements[i]).append(" ");
                }

                productInfoList.add(new ProductInfo(id, productName.toString().trim(), price, amount));
            }
        }
    }
}
