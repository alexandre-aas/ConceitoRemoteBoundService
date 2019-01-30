package com.s.d.a.a.conceitoremoteboundservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import android.os.Messenger;

public class ServicoRemoto extends Service {

    public ServicoRemoto() {
    }

    class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {

            Bundle data = msg.getData();
            String dataString = data.getString("ChaveHandler");
            Toast.makeText(getApplicationContext(), dataString, Toast.LENGTH_SHORT).show();
        }
    }

    final Messenger mensageiro = new Messenger(new IncomingHandler());

    @Override
    public IBinder onBind(Intent intent) {
        return mensageiro.getBinder();
    }
}
