package com.s.d.a.a.conceitoremoteboundservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.View;

public class RemoteBoundServiceActivity extends AppCompatActivity {
    Messenger mensageiro = null;
    boolean isBound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_bound_service);

        Intent intent = new Intent(getApplicationContext(), ServicoRemoto.class);

        bindService(intent, servicoDeConexao, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection servicoDeConexao = new ServiceConnection() {
                public void onServiceConnected(
                        ComponentName className,
                        IBinder service) {
                    mensageiro = new Messenger(service);
                    isBound = true;
                }

                public void onServiceDisconnected(ComponentName className) {
                    mensageiro = null;
                    isBound = false;
                }
            };

    public void enviarMensagem(View view) {
        //Se o serviço não estiver conectado, sai.
        if (!isBound) return;

        Message msg = Message.obtain();

        Bundle bundle = new Bundle();
        bundle.putString("ChaveHandler", "Mensagem recebida");

        msg.setData(bundle);

        try {
            mensageiro.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
