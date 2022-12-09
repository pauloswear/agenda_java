import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;


public class InterfaceAgenda {
    public JFrame jfAgenda;
    public JButton btoInserir, btoExcluir, btoAlterar, btoConsultar;
    public JLabel lblNome, lblCelular;
    public JTextField tfNome, tfCelular;
    public JPanel pnBotao, pnDados;




    public void desenhaJanela(){
        btoInserir = new JButton("Inserir");
        btoConsultar = new JButton("Consultar");
        btoAlterar = new JButton("Alterar");
        btoExcluir = new JButton("Excluir");

        lblNome = new JLabel("Nome ");
        lblCelular = new JLabel("Celular ");

        tfNome = new JTextField(40);
        tfCelular = new JTextField(15);

        jfAgenda = new JFrame("Agenda");
        jfAgenda.setLayout(new BorderLayout());

        pnBotao = new JPanel();
        pnBotao.setLayout(new FlowLayout());

        pnDados = new JPanel();
        pnDados.setLayout(new GridLayout(2,2));


        pnDados.add(lblNome);
        pnDados.add(tfNome);
        pnDados.add(lblCelular);
        pnDados.add(tfCelular);

        pnBotao.add(btoInserir);
        pnBotao.add(btoConsultar);
        pnBotao.add(btoAlterar);
        pnBotao.add(btoExcluir);

        jfAgenda.add(pnDados, BorderLayout.CENTER);
        jfAgenda.add(pnBotao, BorderLayout.SOUTH);

        
        jfAgenda.setSize(400, 130);
        jfAgenda.setLocation(300,200);
        jfAgenda.setVisible(true);


        Listener listener = new Listener();

        btoInserir.addActionListener(listener);
        btoConsultar.addActionListener(listener);
        btoAlterar.addActionListener(listener);
        btoExcluir.addActionListener(listener);


    }



    class Listener implements ActionListener {


        
        String msg;
        String servidor_banco = "jdbc:mysql://localhost:3306/bdteste";
        String usuario_banco = "root";
        String senha_banco = "";
        ConnectionMYSQL bd = new ConnectionMYSQL();



        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == btoInserir) {
                String nome = tfNome.getText();
                String celular = tfCelular.getText();
        
                msg = bd.conectaBanco(
                    this.servidor_banco,
                    this.usuario_banco, 
                    this.senha_banco
                );
        
                System.out.println("Conexão banco: "+msg);
                System.out.println("Nome a ser inserido: "+nome);
                System.out.println("Celular a ser inserido: "+celular);
                msg = bd.insere("insert into tbusuario (nome,celular)"+" values ('"+nome+"','"+celular+"')");
                showMessageDialog(null, msg);


            }
        
        
            
            if (e.getSource() == btoConsultar){
                msg = bd.conectaBanco(
                    this.servidor_banco,
                    this.usuario_banco, 
                    this.senha_banco
                );
            
                String[] msg_arr = bd.consulta("select * from tbusuario");
                if (msg_arr[0] == null) {
                    showMessageDialog(null, "Não encontrado nenhum cadastro desse usuário");                
                }
                else {
                    showMessageDialog(null, "Nome: " + msg_arr[0] + "\n" + "Celular: " + msg_arr[1]);
                }


            }
            
            
            
            if (e.getSource() == btoAlterar){
                String novoNome = JOptionPane.showInputDialog("Digite o novo nome");
                String novoNumero = JOptionPane.showInputDialog("Digite o novo número do celular");
                String nome = tfNome.getText();
                String celular = tfCelular.getText();
                ConnectionMYSQL bd = new ConnectionMYSQL();
                msg = bd.conectaBanco(
                    this.servidor_banco,
                    this.usuario_banco, 
                    this.senha_banco
                );

                msg = bd.altera("UPDATE bdteste.tbusuario SET nome = '"+novoNome+"', celular = '"+novoNumero+"' WHERE tbusuario.nome = '"+nome+"' AND tbusuario.celular = '"+celular+"' LIMIT 1 ;");
                System.out.println("Alteração de dados: "+msg);
                showMessageDialog(null, msg);
            
            }
            
            
            
            if (e.getSource() == btoExcluir){
                String nome = tfNome.getText();
                msg = bd.conectaBanco(
                    this.servidor_banco,
                    this.usuario_banco, 
                    this.senha_banco
                );
                
                System.out.println("Conexão banco: "+msg);
                msg = bd.exclui("delete from tbusuario where nome='"+nome+"';");
                showMessageDialog(null, msg);

            }
            
        
        }
        


    }

}


