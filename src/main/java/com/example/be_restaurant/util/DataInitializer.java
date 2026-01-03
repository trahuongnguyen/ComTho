package com.example.be_restaurant.config;

import com.example.be_restaurant.entity.Category;
import com.example.be_restaurant.entity.Food;
import com.example.be_restaurant.repository.CategoryRepository;
import com.example.be_restaurant.repository.FoodRepository;
import com.example.be_restaurant.repository.UserRepository;
import com.example.be_restaurant.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final FoodRepository foodRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        initSystemAdmin();
        initCategories();
        initFoods();
    }

    // ================= USER =================
    private void initSystemAdmin() {
        userRepository.findByUsernameAndStatus("systemAdmin", true)
                .orElseGet(() -> {
                    User user = new User();
                    user.setUsername("systemAdmin");
                    user.setPassword(passwordEncoder.encode("systemAdmin"));
                    user.setRole(User.Role.ADMIN);
                    user.setFullName("System Admin");
                    user.setStatus(true);
                    user.setCreatedAt(LocalDateTime.now());
                    user.setUpdatedAt(LocalDateTime.now());
                    return userRepository.save(user);
                });
    }

    // ================= CATEGORY =================
    private void initCategories() {
        List<String> categories = List.of(
                "Món đặc biệt",
                "Cơm thố heo",
                "Cơm thố gà",
                "Cơm thố bò",
                "Cơm thố mix vị",
                "Cơm trắng",
                "Món gọi thêm",
                "Đồ uống"
        );

        for (String name : categories) {
            categoryRepository.findByNameAndStatus(name, true)
                    .orElseGet(() -> {
                        Category category = new Category();
                        category.setName(name);
                        category.setStatus(true);
                        category.setCreatedAt(LocalDateTime.now());
                        category.setUpdatedAt(LocalDateTime.now());
                        return categoryRepository.save(category);
                    });
        }
    }

    // ================= FOOD =================
    private void initFoods() {

        Category monDacBiet = getCategory("Món đặc biệt");
        Category comThoHeo = getCategory("Cơm thố heo");
        Category comThoGa = getCategory("Cơm thố gà");
        Category comThoBo = getCategory("Cơm thố bò");
        Category comThoMix = getCategory("Cơm thố mix vị");
        Category comTrang = getCategory("Cơm trắng");
        Category monThem = getCategory("Món gọi thêm");
        Category doUong = getCategory("Đồ uống");

        // ---- Món đặc biệt ----
        food("Thố đặc biệt", 70000, false, monDacBiet);
        food("Bánh mì chảo xá xíu", 50000, false, monDacBiet);
        food("Bánh mì chảo bò", 55000, false, monDacBiet);
        food("Bánh mì chảo ngỗng", 55000, false, monDacBiet);
        food("Bánh mì thêm", 5000, false, monDacBiet);
        food("Pate - 1 miếng", 10000, false, monDacBiet);
        food("Khoai tây - 100g", 10000, false, monDacBiet);
        food("Xúc xích - 1 cái", 10000, false, monDacBiet);
        food("Thịt bò - 100g", 50000, false, monDacBiet);
        food("Thịt xá xíu - 100g", 45000, false, monDacBiet);
        food("Thịt ngỗng - 100g", 50000, false, monDacBiet);

        // ---- Cơm thố heo ----
        food("Thố xá xíu", 50000, true, comThoHeo);
        food("Thố sườn nướng", 55000, true, comThoHeo);
        food("Thố sườn sốt chua ngọt", 55000, true, comThoHeo);
        food("Thố sườn sốt cay", 55000, true, comThoHeo);
        food("Thố ốp la", 30000, true, comThoHeo);
        food("Thố rau củ", 20000, true, comThoHeo);

        // ---- Cơm thố gà ----
        food("Thố gà áp chảo", 45000, true, comThoGa);
        food("Thố đùi gà nướng", 55000, true, comThoGa);
        food("Thố lườn ngỗng", 55000, true, comThoGa);
        food("Thố gà xào nấm", 55000, true, comThoGa);
        food("Thố gà xào dứa", 55000, true, comThoGa);
        food("Thố gà sốt chua ngọt", 50000, true, comThoGa);
        food("Thố gà sốt cay", 50000, true, comThoGa);

        // ---- Cơm thố bò ----
        food("Thố bò xào lăn", 50000, true, comThoBo);
        food("Thố bò xào nấm", 55000, true, comThoBo);
        food("Thố bò xào đậu đũa", 55000, true, comThoBo);
        food("Thố bò sốt tiêu đen", 55000, true, comThoBo);

        // ---- Cơm thố mix ----
        food("Thố bò - xíu", 55000, true, comThoMix);
        food("Thố bò - gà", 55000, true, comThoMix);
        food("Thố ngỗng - xíu", 65000, true, comThoMix);
        food("Thố sườn - xíu", 65000, true, comThoMix);
        food("Thố gà - xíu", 55000, true, comThoMix);

        // ---- Cơm trắng ----
        food("Trắng xá xíu", 45000, false, comTrang);
        food("Trắng sườn nướng", 50000, false, comTrang);
        food("Trắng sườn chua ngọt", 50000, false, comTrang);
        food("Trắng sườn cay", 50000, false, comTrang);
        food("Trắng lườn ngỗng", 55000, false, comTrang);
        food("Trắng đùi gà nướng", 50000, false, comTrang);
        food("Trắng gà áp chảo", 45000, false, comTrang);

        // ---- Món gọi thêm ----
        food("Trứng ốp la", 10000, false, monThem);
        food("Trứng tráng hành", 15000, false, monThem);
        food("Bắp cải xào", 25000, false, monThem);
        food("Bắp cải luộc", 25000, false, monThem);
        food("Đậu đũa xào", 25000, false, monThem);
        food("Đậu đũa luộc", 25000, false, monThem);

        // ---- Đồ uống ----
        food("Trà quất", 15000, false, doUong);
        food("Sữa đậu nành", 12000, false, doUong);
        food("Coca", 15000, false, doUong);
        food("Pepsi", 15000, false, doUong);
        food("7up", 15000, false, doUong);
        food("Bò húc", 18000, false, doUong);
        food("Nước Dasani", 10000, false, doUong);
        food("Bia Sài Gòn", 20000, false, doUong);
    }

    // ================= HELPER =================
    private Category getCategory(String name) {
        return categoryRepository.findByNameAndStatus(name, true)
                .orElseThrow(() -> new RuntimeException("Category not found: " + name));
    }

    private void food(String name, double price, boolean canUpSize, Category category) {
        if (!foodRepository.existsByNameAndCategory(name, category)) {
            Food food = new Food();
            food.setName(name);
            food.setPrice(price);
            food.setCanUpSize(canUpSize);
            food.setUpSizePrice(15000.0);
            food.setCategory(category);
            food.setStatus(true);
            food.setCreatedAt(LocalDateTime.now());
            food.setUpdatedAt(LocalDateTime.now());
            foodRepository.save(food);
        }
    }
}
