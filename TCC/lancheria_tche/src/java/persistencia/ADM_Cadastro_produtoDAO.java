package persistencia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Cadastro_produto;

public class ADM_Cadastro_produtoDAO extends Conexao {

    public ADM_Cadastro_produtoDAO() throws SQLException {
        super();
    }

    public void inserir(Cadastro_produto conta) throws SQLException {
        this.setPreparedStatement("insert into produtos(nomeprod, tipo, valor) values(?, ?, ?)");
        this.getPreparedStatement().setString(1, conta.getLanche());
        this.getPreparedStatement().setString(2, conta.getTipo());
        this.getPreparedStatement().setFloat(3, conta.getPrecolanche());
        this.getPreparedStatement().executeUpdate();
    }

    public void alterar(Cadastro_produto conta) throws SQLException {
        this.setPreparedStatement("update produtos set nomeprod=?, tipo=?, valor=? where idproduto=?");
        this.getPreparedStatement().setString(1, conta.getLanche());
        this.getPreparedStatement().setString(2, conta.getTipo());
        this.getPreparedStatement().setFloat(3, conta.getPrecolanche());
        this.getPreparedStatement().setInt(4, conta.getId());
        this.getPreparedStatement().executeUpdate();
    }

    public void excluir(Cadastro_produto conta) throws SQLException {
        this.setPreparedStatement("delete from produtos where idproduto=?");
        this.getPreparedStatement().setInt(1, conta.getId());
        this.getPreparedStatement().executeUpdate();
    }

    public Cadastro_produto getContaPorID(int id) throws SQLException {
        Cadastro_produto conta = new Cadastro_produto();
        this.setPreparedStatement("select * from produtos where idproduto = ?");
        this.getPreparedStatement().setInt(1, id);
        this.setResultSet(getPreparedStatement().executeQuery());
        while (this.getResultSet().next()) {
            conta.setId(getResultSet().getInt("idproduto"));
            conta.setLanche(getResultSet().getString("nomeprod"));
            conta.setTipo(getResultSet().getString("tipo"));
        }
        return conta;
    }

    public Cadastro_produto getContaPorNome(String nome) throws SQLException {
        Cadastro_produto conta = new Cadastro_produto();
        this.setPreparedStatement("select * from produtos where nomeprod = ?");
        this.getPreparedStatement().setString(1, nome);
        this.setResultSet(getPreparedStatement().executeQuery());
        while (getResultSet().next()) {
            conta.setId(getResultSet().getInt("idproduto"));
            conta.setLanche(getResultSet().getString("nomeprod"));
            conta.setTipo(getResultSet().getString("tipo"));
        }
        return conta;
    }

    public List<Cadastro_produto> getContas(String sql) throws SQLException {
        List<Cadastro_produto> contas = new ArrayList<>();
        this.setPreparedStatement(sql);
        this.setResultSet(this.getPreparedStatement().executeQuery());
        while (this.getResultSet().next()) {
            Cadastro_produto conta = new Cadastro_produto();
            conta.setId(this.getResultSet().getInt("idproduto"));
            conta.setLanche(this.getResultSet().getString("nomeprod"));
            conta.setTipo(this.getResultSet().getString("tipo"));
            conta.setPrecolanche(this.getResultSet().getFloat("valor"));
            contas.add(conta);
        }
        return contas;
    }

}
