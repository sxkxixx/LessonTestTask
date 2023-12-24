package example.bot;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class FakeBot implements Bot {
    private List<String> messages;

    public FakeBot() {
        this.messages = new ArrayList<>();
    }

    /**
     * Отправка сообщения пользователю
     *
     * @param chatId  идентификатор чата
     * @param message текст сообщения
     */
    @Override
    public void sendMessage(Long chatId, String message) {
        this.messages.add(message);
    }

    /**
     * Возвращает список сообщений
     * @return Список сообщений
     */
    public List<String> getMessages() {
        return this.messages;
    }
}
