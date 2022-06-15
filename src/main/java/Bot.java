import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Bot extends TelegramLongPollingBot {
    private final String BOT_NAME = "";
    private final String BOT_TOKEN = "";

    public static void main(String[] args) throws TelegramApiException {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    //Return last listings
    public void sendMsg(Message message, List<Model> listingList) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        setButtons(sendMessage);
        for (Model listing : listingList) {
            sendMessage.setText("Price: " + listing.getCurrentPrice() + "$\nCondition: " + listing.getConditionDisplayName() + "\n" + listing.getTitle() + "\n" + listing.getViewItemURL());
            execute(sendMessage);
        }
    }

    //Return text message
    public void sendMsg(Message message, String example) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        setButtons(sendMessage);
        sendMessage.setText(example);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    //Check input message
    @Override
    public void onUpdateReceived(Update update) {
        String pattern = "(\\w*)(\\:)(\\d+)(\\:)(\\d+)(\\:)(\\d+)";
        ReadXmlDomParser parser = new ReadXmlDomParser();
        Message message = update.getMessage();
        if (message.getText().equals("Request Example")) {
            sendMsg(message, "Iphone 12 Pro Max:300:500:5");
        } else if (!Pattern.matches(pattern, message.getText().replaceAll(" ", ""))) {
            sendMsg(message, "Make correct request\uD83D\uDE1C");
        }
        if (message != null && message.hasText()) {
            try {
                sendMsg(message, parser.getListing(message));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    //Create static button
    public void setButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("Request Example"));
        keyboardRowList.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }

    //Telegram bot name
    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    //Telegram bot token
    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }
}
