package gameclient;

import gameserver.IContentProducer;
import gameserver.IContentReceiver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 *  Componente gráfico correspondente à célula do tabuleiro.
 *
 *  Cliques do usuário acionam o método sobrescrito "JButton.fireActionperformed()".
 *
 *  GUICell usa este método para enviar comandos para quaisquer recipientes CommandReceiver,
 *  por meio do método implementado "ICommandProducer.fireCommand()".
 *
 *  Por implementar a interface IContentReceiver, pode receber avisos de ContentProducers
 *  de que houve uma atualização no conteúdo destes, via "IContentReceiver.noticeContentUpdate()".
 *
 *  Por fim, pode armazenar um ponteiro para um objeto ContentProducer, e por meio deste inquirir
 *  sobre seu conteúdo após receber um aviso de atualização, via "IContentProducer.sendContent()".
 */

public class GUICell extends JButton implements ICommandProducer, IContentReceiver {
    Point pos;
    ICommandReceiver commandReceiver;
    IContentProducer contentProducer;

    public GUICell(Point pos) {
        super();
        this.pos = pos;
        this.setFocusable(false);                   // Remove interação gráfica indesejável
        this.setBorder(null);                       // Remove bordas
        this.paint(-1);
    }

    public void paint(int playerID) {
        int tone = 255 - 51 * (playerID + 1);
        if (tone < 0) {
            tone = 0;
        }
        this.setBackground(new Color(255, tone, tone));
    }

    // Sobrescreve método de JButton
    @Override
    protected void fireActionPerformed(ActionEvent e) {
        this.fireCommand(this.pos);
    }

    // Implementa métodos de ICommandProducer
    @Override
    public void addCommandReceiver(ICommandReceiver commandReceiver) {
        this.commandReceiver = commandReceiver;
    }

    @Override
    public void fireCommand(Point pos) {
        this.commandReceiver.listenCommand(pos);
    }

    // Implementa métodos de IContentReceiver
    @Override
    public void setContentProducer(IContentProducer contentProducer) {
        this.contentProducer = contentProducer;
    }

    @Override
    public void noticeContentUpdate() {
        assert this.contentProducer != null;
        int playerID = this.contentProducer.sendContent().ordinal() - 1;
        this.paint(playerID);
        this.setEnabled(false);                         // Bloqueia novos cliques
    }
}
