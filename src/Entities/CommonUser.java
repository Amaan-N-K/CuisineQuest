pacakge Entities;
class CommonUser implements User {
    private final String name;
    private final String password;
    private final List<Recipe> favorites;

    CommonUser(String name, String password){
        this.name = name;
        this.password = password;
        this.favorites = new ArrayList<>();
    }
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public List getFavorites() { return favorites; }
}
