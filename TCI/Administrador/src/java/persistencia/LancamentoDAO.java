/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Lancamento;

/**
 *
 * @author emilio
 */
public class LancamentoDAO extends Conexao {

    private Cadastro_produtoDAO contaDAO;

    public LancamentoDAO() throws SQLException {
        super();
        contaDAO = new Cadastro_produtoDAO();
    }

    public void inserir(Lancamento lancamento) throws SQLException {
        this.setPreparedStatement("insert into lancamento(id_contas, historico, data, valor) values(?, ?, ?, ?)");
        this.getPreparedStatement().setInt(1, lancamento.getConta().getId());        
        this.getPreparedStatement().setString(2, lancamento.getHistorico());
        this.getPreparedStatement().setDate(3, new java.sql.Date(lancamento.getData().getTime()));
        this.getPreparedStatement().setFloat(4, lancamento.getValor());
        this.getPreparedStatement().executeUpdate();
    }

    public void alterar(Lancamento lancamento) throws SQLException {
        this.setPreparedStatement("update lancamento set id_contas=?, historico=?, data=?, valor=? where id=?");
        this.getPreparedStatement().setInt(1, lancamento.getConta().getId());
        this.getPreparedStatement().setString(2, lancamento.getHistorico());
        this.getPreparedStatement().setDate(3, new java.sql.Date(lancamento.getData().getTime()));
        this.getPreparedStatement().setFloat(4, lancamento.getValor());
        this.getPreparedStatement().setInt(5, lancamento.getId());
        this.getPreparedStatement().executeUpdate();
    }

    public void excluir(Lancamento lancamento) throws SQLException {
        this.setPreparedStatement("delete from lancamento where id=?");
        this.getPreparedStatement().setInt(1, lancamento.getId());
        this.getPreparedStatement().executeUpdate();
    }

    public List<Lancamento> getLancamentos(String sql) throws SQLException {
        List<Lancamento> lancamentos = new ArrayList();
        this.setPreparedStatement(sql);
        this.setResultSet(this.getPreparedStatement().executeQuery());
        while (this.getResultSet().next()) {
            Lancamento lancamento = new Lancamento();
            lancamento.setId(this.getResultSet().getInt("id"));
            lancamento.setConta(contaDAO.getContaPorID(this.getResultSet().getInt("id_contas")));
            lancamento.setData(this.getResultSet().getDate("data"));
            lancamento.setHistorico(this.getResultSet().getString("historico"));
            lancamento.setValor(this.getResultSet().getFloat("valor"));
            lancamentos.add(lancamento);
        }
        return lancamentos;
    }

}
