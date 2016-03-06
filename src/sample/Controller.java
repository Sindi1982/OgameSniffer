package sample;

import com.sun.javaws.progress.Progress;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {

    Task copyWorker;

    @FXML
    private CheckBox checkbox;
    @FXML
    private TextField pontosm, posicao;
    @FXML
    private Button botao1;
    @FXML
    private ProgressBar bar;
    @FXML
    Label label;


    int tam;


    @FXML
    void initialize() {

        ArrayList<String> lista_inativos = new ArrayList<>();


        get_xml apanha = null;
        try {
            apanha = new get_xml();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        try {
            Document doc = apanha.get_specficic_xml("http://s124-pt.ogame.gameforge.com/api/players.xml");
            print_elements.get_inativos(doc, "player", lista_inativos);
            tam = lista_inativos.size();

            System.out.print("------------->" + tam);
            label.setText("Existem " + tam + " jogadores inativos.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void handleButtonAction() throws Exception {


        if (pontosm.getText().isEmpty() || posicao.getText().isEmpty())
            return;
        botao1.setDisable(true);


        copyWorker = createWorker();
        bar.progressProperty().unbind();
        bar.progressProperty().bind(copyWorker.progressProperty());
        label.textProperty().bind(copyWorker.messageProperty());
        Thread thread = new Thread(copyWorker);

        thread.start();


    }

    public Task createWorker() {
        return new Task() {
            @Override
            protected Object call() throws Exception {


                int nlinhas = 0;

                Thread thread_attack = new Thread((new logicath(Integer.parseInt(pontosm.getText()), Integer.parseInt(posicao.getText()))));
                thread_attack.start();

                for (int i = 0; i < tam; i++) {
                    //System.out.println("------------------------------->"+i);


                    Thread.sleep(180);
                    updateMessage("Á procura de inativos para farmar...");
                    updateProgress(i + 1, tam);


                }

                if (!checkbox.isSelected())
                    nlinhas = Auxiliar.readlinesg2();
                else nlinhas = Auxiliar.readlines();
                botao1.setDisable(false);
                updateMessage("Concluido! Encontrados " + nlinhas + " farms.");

                return true;
            }
        };
    }

}



