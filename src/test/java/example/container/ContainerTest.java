package example.container;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ContainerTest {
    private Container container;

    @Before
    public void initContainer() {
        this.container = new Container();
    }

    @After
    public void destroyMethod() {
        this.container = null;
    }

    /**
     * Тест на добавление
     */
    @Test
    public void testAddMethod() {
        Item firstItem = new Item(1L);
        Item secondItem = new Item(2L);

        this.container.add(firstItem);
        Assert.assertEquals(1, this.container.size());
        this.container.add(secondItem);
        Assert.assertEquals(2, this.container.size());
    }

    /**
     * Тест на удаление
     */
    @Test
    public void testRemoveMethod() {
        Item firstItem = new Item(1L);
        Item secondItem = new Item(2L);
        this.container.add(firstItem);
        this.container.add(secondItem);

        Assert.assertTrue(this.container.remove(firstItem));
        Assert.assertEquals(1, this.container.size());
        Assert.assertTrue(this.container.remove(secondItem));
        Assert.assertEquals(0, this.container.size());
    }

    /**
     * Тест на проверку наличия Item в Container
     */
    @Test
    public void testContainsMethod() {
        Item item = new Item(1L);
        this.container.add(item);

        Assert.assertTrue(this.container.contains(item));
    }

    /**
     * Проверка на получение элемента по индексу
     */
    @Test
    public void testGetByIndexMethod() {
        Item firstItem = new Item(1L);
        this.container.add(firstItem);
        Assert.assertEquals(firstItem, this.container.get(0));
        Item secondItem = new Item(2L);
        this.container.add(secondItem);
        Assert.assertEquals(secondItem, this.container.get(1));
    }
}
