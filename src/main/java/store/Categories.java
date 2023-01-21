package store;

public enum Categories {
   HOME("home"), FOOD("food"), TRAVEL("travel"), ENTERTAIMENT("entertainment"), OTHER("other");

    public String getCategoryName() {
        return categoryName;
    }

    private String categoryName;
    private Categories(String categoryName) {
        this.categoryName = categoryName;
    }
}
