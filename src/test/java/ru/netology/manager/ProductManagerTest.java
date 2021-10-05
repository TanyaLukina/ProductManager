package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ProductManagerTest {
    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);
    private Product book1 = new Book(1, "English", 1000, "Murfy");
    private Product book2 = new Book(2, "Alphabet", 700, "Petrova");
    private Product book3 = new Book(3, "Cinderella", 500, "Murfy");
    private Product phone1 = new Smartphone(4, "Iphone", 109000, "Apple");
    private Product phone2 = new Smartphone(5, "Samsung Galazy Z", 160000, "Samsung");

    @BeforeEach
    public void manageAdd() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(phone1);
        manager.add(phone2);

    }

    @Test
    public void shouldSearchByBook() {
        Product[] actual = manager.searchBy("Murfy");
        Product[] expected = new Product[]{book1, book3};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByPhone() {
        Product[] actual = manager.searchBy("Iphone");
        Product[] expected = new Product[]{phone1};

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindExistingBookByName() {
        String nameBook = "Cinderella";
        Product[] expected = new Product[]{book3};
        Product[] actual = manager.searchBy(nameBook);

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindExistingPhoneByName() {
        String namePhone = "Samsung Galazy Z";
        Product[] expected = new Product[]{phone2};
        Product[] actual = manager.searchBy(namePhone);

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindExistingPhoneByCreator() {
        String creatorPhone = "Iphone";
        Product[] expected = new Product[]{phone1};
        Product[] actual = manager.searchBy(creatorPhone);
        assertArrayEquals(expected, actual);

    }


    @Test
    void shouldSearchByWhenMissingProduct() {
        Product[] actual = manager.searchBy("нет такого продукта");
        Product[] expected = new Product[]{};
        assertArrayEquals(expected, actual);


    }
}