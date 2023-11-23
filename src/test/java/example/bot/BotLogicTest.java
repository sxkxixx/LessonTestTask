package example.bot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Класс для тестирования {@link BotLogic}
 */
public class BotLogicTest {
    User user;
    FakeBot bot;
    BotLogic logic;

    /**
     * Инициализирует пользователя, бота и логику бота перед каждый тестом
     */
    @Before
    public void init() {
        this.user = new User(1L);
        this.bot = new FakeBot();
        this.logic = new BotLogic(this.bot);
    }

    /**
     * Тест команды /test с правильными ответами
     */
    @Test
    public void processCommandTest() {
        logic.processCommand(this.user, "/test");
        Assert.assertEquals("Вычислите степень: 10^2", this.bot.getMessages().get(0));
        Assert.assertEquals(State.TEST, this.user.getState());

        logic.processCommand(this.user, "100");
        Assert.assertEquals("Правильный ответ!", this.bot.getMessages().get(1));
        Assert.assertEquals("Сколько будет 2 + 2 * 2", this.bot.getMessages().get(2));

        logic.processCommand(this.user, "6");
        Assert.assertEquals("Правильный ответ!", this.bot.getMessages().get(3));
        Assert.assertEquals("Тест завершен", this.bot.getMessages().get(4));
        Assert.assertEquals(State.INIT, this.user.getState());
    }

    /**
     * Тест команды /test с неправильными ответами
     */
    @Test
    public void processCommandTestIfAnswerIncorrect() {
        logic.processCommand(this.user, "/test");

        logic.processCommand(this.user, "101");
        Assert.assertEquals("Вы ошиблись, верный ответ: 100", this.bot.getMessages().get(1));

        logic.processCommand(this.user, "6");
        Assert.assertEquals("Тест завершен", this.bot.getMessages().get(4));
        Assert.assertEquals(State.INIT, this.user.getState());

        Assert.assertEquals(1, this.user.getWrongAnswerQuestions().size());
        Assert.assertEquals("Вычислите степень: 10^2", this.user.getWrongAnswerQuestions().get(0).getText());
    }

    /**
     * Тест команды /notify
     */
    @Test
    public void processCommandNotify() throws InterruptedException {
        this.logic.processCommand(this.user, "/notify");
        Assert.assertEquals("Введите текст напоминания", this.bot.getMessages().get(0));

        this.logic.processCommand(this.user, "ХЫХЫХЫХЫХ");
        Assert.assertNotNull(this.user.getNotification());
        Assert.assertEquals("Через сколько секунд напомнить?", this.bot.getMessages().get(1));

        this.logic.processCommand(this.user, "2");
        Assert.assertEquals("Напоминание установлено", this.bot.getMessages().get(2));
        Assert.assertEquals(3, this.bot.getMessages().size());

        Thread.sleep(2050);

        Assert.assertEquals(4, this.bot.getMessages().size());
        Assert.assertEquals("Сработало напоминание: 'ХЫХЫХЫХЫХ'", this.bot.getMessages().get(3));
    }

    /**
     * Тест команды /repeat
     */
    @Test
    public void processCommandRepeat() {
        this.logic.processCommand(this.user, "/test");
        this.logic.processCommand(this.user, "101");
        this.logic.processCommand(this.user, "6");

        this.logic.processCommand(this.user, "/repeat");
        Assert.assertEquals("Вычислите степень: 10^2", this.bot.getMessages().get(5));
        Assert.assertEquals(State.REPEAT, user.getState());

        this.logic.processCommand(this.user, "100");
        Assert.assertEquals("Правильный ответ!", this.bot.getMessages().get(6));
        Assert.assertEquals("Тест завершен", this.bot.getMessages().get(7));
        Assert.assertEquals(State.INIT, this.user.getState());
    }
}