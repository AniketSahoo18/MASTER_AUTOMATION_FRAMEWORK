package enums;

public enum PageUrls {

    LETS_SHOP_URL("https://rahulshettyacademy.com/client");

    private final String urlName;

    PageUrls(String name) {
        this.urlName = name;
    }

    @Override
    public String toString() {
        return urlName;
    }
}
