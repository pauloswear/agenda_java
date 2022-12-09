import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ConnectionMYSQL {
    public Connection conexao = null;
    public Statement stm = null;
    public ResultSet rs = null;



    public String conectaBanco(String servidor, String usuario, String senha){
        String msg = "";

        try {
            conexao=DriverManager.getConnection(servidor, usuario, senha);
            stm = conexao.createStatement();
            msg = "Conexao com o banco de dados realizada com sucesso";

        } 
        
        catch(Exception e) {
            msg = "Erro de conexão com o banco de dados " + e.getMessage();
        }

        return msg;

    }



    public String insere(String query){

        String msg;

        try {
            stm.executeUpdate(query);
            msg = "Comando executado com sucesso!";

        } 
        
        catch (SQLException e) {
            e.printStackTrace();
            msg = "Erro na conexão com o banco de dados";
        }

        return msg;

    }



    public String[] consulta(String query){
        ResultSet rs = null;
        String msg = null;

        String[] arr = new String[2];


        try {
            rs = stm.executeQuery(query);

            while (rs.next()){
                String nome = rs.getString(1);
                String celular = rs.getString(2);
                arr[0] = nome;
                arr[1] = celular;
                return arr;
            }

        } 
        
        catch (Exception e){
            msg = "Erro na consulta " + e.getMessage();
        }
        
        arr[0] = msg;
        return arr;
    }



    public String altera(String query){
        String msg;

        try{
            stm.executeUpdate(query);
            msg = "Alteração com sucesso!";
        } 
        
        catch (Exception e){
            msg = "Falha na alteração de dados";
            e.printStackTrace();
        }

        return msg;

    }



    public String exclui(String query){
        String msg;

        try {
            stm.executeUpdate(query);
            msg = "Exclusão efetuada com sucesso!";
        } 
        
        catch (Exception e) {
            e.printStackTrace();
            msg = "Erro na exclusão";

        }

        return msg;

    }



}

