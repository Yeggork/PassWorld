package com.example.passworld;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import com.example.passworld.database.DBManager;
import com.example.passworld.non.Passworddd;
import com.example.passworld.non.PasswordddAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Checked extends MainActivity {
    private EditText password;
    private boolean[] flags = new boolean[20]; // Массив для хранения флагов
    private DBManager dbManager;
    private TextView tvQuest;
    private Passworddd passworddd = new Passworddd();
    private static final long DELAY = 60000; // таймер 1 минута
    private static Timer timer;
    String[] question = {"1) Ваш пароль должен состоять как минимум из 8 символов","2) Ваш пароль должен содержать букву в верхнем регистре","3) Ваш пароль должен содержать хотя бы 1 символ","4) Ваш пароль должен содержать цифру",
    "5) Ваш пароль должен содержать Римскую цифру","6) Сумма цифр вашего пароль должна равняться - 31","7) Ваш пароль должен содержать хотя бы один символьный смайлик","8) Ваш пароль должен иметь хотя бы одну английскую букву",
    "9) Ваш пароль должен содержать ответ на загадку: \"Кто ходит сидя?\"","10) Для работы приложения требуется включенный вайфай!","11) Ваш пароль должен содержать название символа с кодом 0 из кодировки ASCII",
    "12) О нет! Ваш пароль поймал вирус! Из-за него каждые 30 секунд - удаляется рандомный символ! Чтобы подтвердить получение данной информации введите кодовое слово \"подтвержаю\"","13) А теперь с осознание того что у тебя в пароле вирус, тебе стоит спокойно отдохнуть (подождите 1 минуту ничего не делая)",
    "14) Для работы приложения требуется больше заряда...","15) Ответьте (да или нет) на вопрос: Вам более 18 лет?","16) количество символов пароля должно быть кратно 4",
    "17) Ваш пароль должен содержать текущее время на вашем уствойстве","18) Количество цифр вашего пароля должно быть нечетным","19) Ваш пароль должен содержать 3-ех символьный банковский код любой валюты","20) Пароль слишком большой(((","Поздравляю!!! Вы создали надежный пароль который никто никогда не угадает.Не забудьте сохранить его!"};

    // Логика проверки на заглавную букву
    private boolean isUpperCase(char ch) {
        return Character.isUpperCase(ch);
    }

    // Логика проверки на цифру
    private boolean isDigit(char ch) {
        return Character.isDigit(ch);
    }
    public static boolean EnglishLetter(String str) {
        for (char c : str.toCharArray()) {
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                return true;
            }
        }
        return false;
    }

    public static boolean isWifiEnable(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

        if (wifiManager != null) {
            return wifiManager.isWifiEnabled();
        }
        return false;
    }
    public static boolean BatteryAbove50Percent(Context context) {
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, ifilter);
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        float batteryPct = level / (float)scale;

        return batteryPct > 0.5;
    }
    public static boolean LenghtKratno4(String text) {
        return text.length() % 4 == 0;
    }

    String otvetZagadka = "шахматист";
    String ASCIIquestion = "null";
    String PODTVERZDAU = "подтверждаю";
    boolean stopsleep = false;
    boolean stopsleepcomplited = false;
    String questionDa = "да";
    String questionNet = "нет";
    int countDigitsinQuestion18 = 0;
    String[] wordsBankCode = {"USD", "EUR", "JPY", "GBP", "AUD", "RUB", "NZD", "BRL", "KZT", "SGD", "CNY"};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle arguments = getIntent().getExtras();
        Context contex = null;

        if(arguments!=null){
            passworddd = (Passworddd) arguments.getSerializable(Passworddd.class.getSimpleName());
        }
        dbManager = new DBManager(this);
        dbManager.openDb();

        password = findViewById(R.id.editTextPassword);
        FloatingActionButton actionButton = findViewById(R.id.actionButtonSavePas);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dbManager.checkPassword(passworddd))
                    dbManager.addPassword(passworddd);
                else{
                    dbManager.updatePassword(passworddd);
                    passworddd = dbManager.getPassword(passworddd);
                }

               Log.d("ss",dbManager.getPasswords().size()+"");
            }
        });
        tvQuest = findViewById(R.id.QuestionTextView);
        tvQuest.setText(question[0]);
        PasswordddAdapter.OnDeleteClickListener onDeleteClickListener = new PasswordddAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(Passworddd password, int position) {
                dbManager.deletePassword(password);
            }
        };
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                passworddd.setTextpassword(password.getText().toString());
                boolean has8sumbol = false;
                boolean hasUppercase = false;
                boolean hasChar = false;
                boolean hasDigit = false;
                boolean hasRimInt = false;
                int sumNumber = 0;
                boolean hasSmiles = false;
                boolean hasEnglish = false;
                boolean hasPuzzle = false;
                boolean hasWiFi = false;
                boolean hasASCII = false;
                boolean hasVIRUS = false;                      //доделать
                boolean hasSleep = false;
                boolean hasPersentOfPhone = false;
                boolean hasQuestion = false;
                boolean hasSumbolKratno4 = false;
                boolean hasCurrentTimee = false;
                boolean hasCharNoChetni = false;
                boolean hasRUB_EUR = false;
                boolean hasBigPassword = false;
                boolean hasUNREAL = false;

                for (int i = 0; i < password.length(); i++) {
                    // Проверка на 8 символвов
                    if (passworddd.getTextpassword().length() > 7) {
                        has8sumbol = true;
                        tvQuest.setText(question[1]);
                    }

                    // Проверка на заглавную букву
                    if (isUpperCase(passworddd.getTextpassword().charAt(i))) {
                        hasUppercase = true;
                        tvQuest.setText(question[2]);
                    }

                    // Проверка на цифру
                    if (isDigit(passworddd.getTextpassword().charAt(i))) {
                        hasDigit = true;
                        tvQuest.setText(question[4]);
                    }

                    //проверка на символ
                    if (isChar(passworddd.getTextpassword())) {
                        hasChar = true;
                        tvQuest.setText(question[3]);
                    }

                    // Проверка на римскую цифру
                    if (Pattern.matches("^[IVXLCDM]+$", passworddd.getTextpassword())) {
                        hasRimInt = true;
                        tvQuest.setText(question[5]);
                    }

                    // Вычисление суммы чисел
                    if (Character.isDigit(passworddd.getTextpassword().charAt(i))) {
                        sumNumber += Character.getNumericValue(passworddd.getTextpassword().charAt(i));
                        tvQuest.setText(question[6]);
                    }

                    //проверка на английскую букву
                    if (EnglishLetter(passworddd.getTextpassword())) {
                        hasEnglish = true;
                        tvQuest.setText(question[8]);
                    }

                    //проверка на правильный ответ на загадку
                    if (passworddd.getTextpassword().toLowerCase().contains(otvetZagadka.toLowerCase())) {
                        hasPuzzle = true;
                        tvQuest.setText(question[9]);
                    }

                    //проврека на включенный вайфай
                    if (isWifiEnable(contex)) {
                        hasWiFi = true;
                        tvQuest.setText(question[10]);
                    }

                    //проверка на наличие фразы null в пароле
                    if (passworddd.getTextpassword().toLowerCase().contains(ASCIIquestion.toLowerCase())) {
                        hasASCII = true;
                        tvQuest.setText(question[11]);
                    }

                    //проверка на подтверждение получение вируса который удаляет символ
                    if (passworddd.getTextpassword().toLowerCase().contains(PODTVERZDAU.toLowerCase())) {
                        hasVIRUS = true;
                        tvQuest.setText(question[12]);
                    }

                    //проверка на то что прошло время отдыха
                    if (hasVIRUS == true & stopsleep == false) {
                        timer = new Timer();
                        TimerTask task = new TimerTask() {
                            @Override
                            public void run() {
                                stopsleepcomplited = true;
                            }
                        };
                        timer.schedule(task, 60000);

                        if (stopsleepcomplited == true) {
                            stopsleep = true;
                            hasSleep = true;
                            tvQuest.setText(question[13]);
                        }
                    }

                    //проверка на более 50 процентов
                    if (BatteryAbove50Percent(contex)) {
                        hasPersentOfPhone = true;
                        tvQuest.setText(question[14]);
                    }

                    //проверка что есть ответ да нет
                    if (passworddd.getTextpassword().toLowerCase().contains(questionDa.toLowerCase()) || passworddd.toLowerCase().contains(questionNet.toLowerCase())) {
                        hasQuestion = true;
                        tvQuest.setText(question[15]);
                    }

                    //проверка что число символо кратно 4
                    if (LenghtKratno4(passworddd.getTextpassword())) {
                        hasSumbolKratno4 = true;
                        tvQuest.setText(question[16]);
                    }

                    //проверка на то что присудствует текущее время телефона
                    if (hasSumbolKratno4) {
                        Pattern pattern = Pattern.compile("\\b\\d{2}:\\d{2}\\b");
                        Matcher matcher = pattern.matcher(passworddd.getTextpassword());
                        while (matcher.find()) {
                            String match = matcher.group();
                            LocalTime currentTime = LocalTime.now();
                            LocalTime timeInString = LocalTime.parse(match);
                            if (currentTime.equals(timeInString)) {
                                hasCurrentTimee = true;
                                tvQuest.setText(question[17]);
                            }
                        }
                    }

                    //счетчик чтобы выполнять проверку которая ниже(что число цифр нечетное)
                    if (Character.isDigit(passworddd.getTextpassword().charAt(i))) {
                        countDigitsinQuestion18++;
                    }
                    //проверка что количество цифр нечетно
                    if (countDigitsinQuestion18 % 2 != 0) {
                        hasCharNoChetni = true;
                        tvQuest.setText(question[18]);
                    }

                    //провекра на наличие банковских кодов
                    for(String word : wordsBankCode) {
                        if(passworddd.getTextpassword().contains(word)) {
                            hasRUB_EUR = true;
                            tvQuest.setText(question[19]);
                            break;
                        }
                    }

                    //проверка на менее 50 символов (число может измениться)
                    if (passworddd.getTextpassword().length() < 51){
                       hasBigPassword = true;
                       tvQuest.setText(question[20]);
                    }

                    //чтобы показало сообщение что ты прошел игру (я не уверен выведет ли оно последнее предложение из questions где я написал поздравляю что прошел игру)
                    if (passworddd.getTextpassword().length() > 100000000) {
                        hasUNREAL = true;
                    }
                }
                
                if(passworddd.getTextpassword().equals(""))
                    tvQuest.setText(question[0]);

                // Проверка на наличие смайлов
                Matcher smileMatcher = Pattern.compile("[:;][-']?[(|)DOP]").matcher(passworddd.getTextpassword());
                hasSmiles = smileMatcher.find();
                tvQuest.setText(question[7]);

                // Обновление флагов в зависимости от условий пароля
                updateFlags(has8sumbol, hasUppercase, hasChar, hasDigit, hasRimInt, sumNumber, hasSmiles, hasEnglish, hasPuzzle, hasWiFi, hasASCII, hasVIRUS, hasSleep, hasPersentOfPhone, hasQuestion, hasSumbolKratno4, hasCurrentTimee, hasCharNoChetni, hasRUB_EUR, hasBigPassword, hasUNREAL);
            }
        });
    }

    // Метод для обновления флагов в зависимости от условий пароля
    private void updateFlags(boolean has8sumbol, boolean hasUppercase, boolean hasChar, boolean hasDigit, boolean hasRimInt, int sumNumber, boolean hasSmiles, boolean hasEnglish, boolean hasPuzzle, boolean hasWiFi, boolean hasASCII, boolean hasVIRUS, boolean hasSleep, boolean hasPersentOfPhone, boolean hasQuestion, boolean hasSumbolKratno4, boolean hasCurrentTimee, boolean hasCharNoChetni, boolean hasRUB_EUR, boolean hasBigPassword, boolean hasUNREAL) {
        flags[0] = has8sumbol;
        if(!hasUNREAL)
            tvQuest.setText(question[20]);
        if(!hasBigPassword)
            tvQuest.setText(question[19]);
        if(!hasRUB_EUR)
            tvQuest.setText(question[18]);
        if(!hasCharNoChetni)
            tvQuest.setText(question[17]);
        if(!hasCurrentTimee)
            tvQuest.setText(question[16]);
        if(!hasSumbolKratno4)
            tvQuest.setText(question[15]);
        if(!hasQuestion)
            tvQuest.setText(question[14]);
        if(!hasPersentOfPhone)
            tvQuest.setText(question[13]);
        if(!hasSleep)
            tvQuest.setText(question[12]);
        if(!hasVIRUS)
            tvQuest.setText(question[11]);
        if(!hasASCII)
            tvQuest.setText(question[10]);
        if(!hasWiFi)
            tvQuest.setText(question[9]);
        if(!hasPuzzle)
            tvQuest.setText(question[8]);
        if(!hasEnglish)
            tvQuest.setText(question[7]);
        if(!hasSmiles)
            tvQuest.setText(question[6]);
        if(!(sumNumber == 31))
            tvQuest.setText(question[5]);
        if(!hasRimInt)
            tvQuest.setText(question[4]);
        if(!hasDigit)
            tvQuest.setText(question[3]);
        if(!hasChar)
            tvQuest.setText(question[2]);
        if(!hasUppercase)
            tvQuest.setText(question[1]);
        if(!has8sumbol)
            tvQuest.setText(question[0]);


        if (has8sumbol) {
            flags[1] = hasUppercase;

            if (hasUppercase) {
                flags[2] = hasChar;

                if (hasChar) {
                    flags[3] = hasDigit;

                    if (hasDigit) {
                        flags[4] = hasRimInt;

                        if (hasRimInt) {
                            flags[5] = sumNumber == 31;

                            if (sumNumber == 31) {
                                flags[6] = hasSmiles;

                                if (hasSmiles) {
                                    flags[7] = hasEnglish;

                                    if (hasEnglish) {
                                        flags[8] = hasPuzzle;

                                        if(hasPuzzle) {
                                            flags[9] = hasWiFi;

                                            if (hasWiFi) {
                                                flags[10] = hasASCII;

                                                if(hasASCII) {
                                                    flags[11] = hasVIRUS;

                                                    if(hasVIRUS) {

                                                        flags[12] = hasSleep;

                                                        if(hasSleep) {
                                                            flags[13] = hasPersentOfPhone;

                                                            if(hasPersentOfPhone) {
                                                                flags[14] = hasQuestion;

                                                                if(hasQuestion) {
                                                                    flags[15] = hasSumbolKratno4;

                                                                    if(hasSumbolKratno4) {
                                                                        flags[16] = hasCurrentTimee;

                                                                        if(hasCurrentTimee) {
                                                                            flags[17] = hasCharNoChetni;

                                                                            if(hasCharNoChetni) {
                                                                                flags[18] = hasRUB_EUR;

                                                                                if(hasRUB_EUR) {
                                                                                    flags[19] = hasBigPassword;

                                                                                    if(hasBigPassword) {
                                                                                        flags[20] = hasUNREAL;
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        String currentActivity = this.getLocalClassName();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Вы уверены, что хотите покинуть текущую игру?");
        builder.setCancelable(true);
        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (currentActivity.equals("Checked")) {
                    Intent intent = new Intent(Checked.this, MainActivity.class);
                    startActivities(new Intent[]{intent});
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                    finish();
                }
            }
        });
        builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public static boolean isChar(String str) {
        String specialCharacters = "[^a-zA-Z0-9]";
        return str.matches(".*" + specialCharacters + ".*");
    }
}
