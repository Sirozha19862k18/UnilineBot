import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;


public class Bot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            if (update.getMessage().hasText()) {
                if (update.getMessage().getText().equals("/i_need_help")) {
                    try {
                        execute(sendInlineKeyBoardMessage(update.getMessage().getChatId()));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if (update.hasCallbackQuery()) { //обработка нажатий кнопок
            processCallbackQuery(update);
        }
    }

    @Override
    public String getBotUsername() {
        final String botName="   "; //Задать имя бота
        return botName;
    }

    @Override
    public String getBotToken() {
        final String botToken="    "; //Задать токен бота
        return botToken;
    }

    private void processCallbackQuery(Update update) { //метод обработки нажатий кнопок от CallBaqQuere
        String pointOfchangeUser = update.getCallbackQuery().getData();

        try {
            execute(new SendMessage().setText(CommandList.MessageList(pointOfchangeUser)).setParseMode("HTML")
                    .setChatId(update.getCallbackQuery().getMessage().getChatId()));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        System.out.println(pointOfchangeUser);
    }

    public static SendMessage sendInlineKeyBoardMessage ( long chatId){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton5 = new InlineKeyboardButton();
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow4 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow5 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        keyboardButtonsRow2.add(inlineKeyboardButton2);
        keyboardButtonsRow3.add(inlineKeyboardButton3);
        keyboardButtonsRow4.add(inlineKeyboardButton4);
        keyboardButtonsRow5.add(inlineKeyboardButton5);
        inlineKeyboardButton1.setText("1. Узнать контакты сервиса").setCallbackData("Contacts");
        inlineKeyboardButton2.setText("2. Повышенная остаточная масличность").setCallbackData("HightOil");
        inlineKeyboardButton3.setText("3. Экструдер стреляет").setCallbackData("ExtruderBOOM");
        inlineKeyboardButton4.setText("4. Пресс не давит масло").setCallbackData("PressNotOil");
        inlineKeyboardButton5.setText("5. Фильтр не закрывается\\открывается").setCallbackData("FilterNotOpen");
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        rowList.add(keyboardButtonsRow3);
        rowList.add(keyboardButtonsRow4);
        rowList.add(keyboardButtonsRow5);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return new SendMessage().setChatId(chatId).setText("Варианты действий:").setReplyMarkup(inlineKeyboardMarkup);
    }
}

