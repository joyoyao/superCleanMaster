package com.balaganovrocks.yourmastercleaner.ui;

import android.app.Application;

import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.YandexMetricaConfig;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Инициализация AppMetrica SDK

        YandexMetricaConfig.Builder configBuilder = YandexMetricaConfig.newConfigBuilder("180a173d-5533-4b25-98ff-2ef6301637a2");
        YandexMetrica.activate(getApplicationContext(), configBuilder.build());
        // Отслеживание активности пользователей
        YandexMetrica.enableActivityAutoTracking(this);

        YandexMetricaConfig.Builder configBuilder1 = YandexMetricaConfig.newConfigBuilder("180a173d-5533-4b25-98ff-2ef6301637a2");
        //Реализуйте логику определения того, является ли запуск приложения первым. В качестве критерия вы можете использовать проверку наличия каких-то файлов (настроек, баз данных и др.), которые приложение создает в свой первый запуск.
        boolean isFirstApplicationLaunch = false;
        if (!isFirstApplicationLaunch) {
            //Передайте значение true, если не хотите, чтобы данный пользователь засчитывался как новый
            configBuilder1.handleFirstActivationAsUpdate(true);
        }
        //Создание объекта расширенной конфигурации
        YandexMetricaConfig extendedConfig = configBuilder1.build();
        // Инициализация AppMetrica SDK
        YandexMetrica.activate(getApplicationContext(), extendedConfig);
        // Отслеживание активности пользователей
        YandexMetrica.enableActivityAutoTracking(this);
    }
}