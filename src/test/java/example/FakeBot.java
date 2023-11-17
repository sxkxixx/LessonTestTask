package example;

import example.bot.Bot;

/**
 *
 */
public class FakeBot implements Bot {

    /**
     * Отправка сообщения пользователю
     *
     * @param chatId  идентификатор чата
     * @param message текст сообщения
     */
    @Override
    public void sendMessage(Long chatId, String message) {

    }
}
