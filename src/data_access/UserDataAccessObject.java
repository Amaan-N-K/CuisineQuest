package data_access;

import Entities.User;
import Entities.UserFactory;
import UseCase.SignUp.SignUpDataAccessInterface;
import UseCase.save_favorite.UserDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class UserDataAccessObject implements UserDataAccessInterface, SignUpDataAccessInterface {
    final private File csv;
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<String, User> accounts = new HashMap<>();
    private UserFactory userFactory;
    private String activeUser = "";

    public UserDataAccessObject(String csvPath, UserFactory userFactory) throws IOException {
        this.userFactory = userFactory;
        csv = new File(csvPath);
        headers.put("user_id", 0);
        headers.put("username", 1);
        headers.put("password", 2);
        headers.put("creation_time", 3);
        headers.put("favorite_recipes", 4);
        if (csv.length() == 0) {
            save();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(csv))) {
                String header = reader.readLine();
                assert header.equals("user_id,username,password,creation_time,favorite_recipes");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String userid = col[headers.get("user_id")];
                    String username = col[headers.get("username")];
                    String password = col[headers.get("password")];
                    LocalDateTime creationTime = LocalDateTime.parse(col[headers.get("creation_time")]);

                    List<String> favoriteRecipes = new ArrayList<>();
                    if (headers.get("favorite_recipes") < col.length) {
                        favoriteRecipes = Arrays.asList(col[headers.get("favorite_recipes")].split("\\|"));
                    }

                    User user = userFactory.create(userid, username, password, creationTime);
                    for (String recipe : favoriteRecipes) {
                    user.addFavoriteRecipe(recipe);}
                    accounts.put(userid, user);
                }
            }
        }
    }

    @Override
    public void save(User user) {
        accounts.put(user.getUserId(), user);
        this.save();

    }

    @Override
    public String getActive() {
        return activeUser;
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csv));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (User user : accounts.values()) {
                String favoriteRecipes = String.join("|", user.getFavoriteRecipes());
                String line = String.format("%s,%s,%s,%s,%s", user.getUserId(), user.getUsername(), user.getPassword(),
                        user.getCreationTime(), favoriteRecipes);
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existByUsername(String username) {
        return accounts.values().stream().anyMatch(user -> user.getUsername().equals(username));
    }

    @Override
    public boolean existsByID(String userId) {
        return accounts.containsKey(userId);
    }

    @Override
    public User getByID(String userId) {
        return accounts.get(userId);
    }

    @Override
    public void setActive(User user) {
        this.activeUser = user.getUserId();
    }
}
