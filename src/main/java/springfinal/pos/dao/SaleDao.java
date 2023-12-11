package springfinal.pos.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import springfinal.pos.domain.Sale;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class SaleDao {
    final private JdbcTemplate jdbcTemplate;

    @Autowired
    public SaleDao(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insert(Sale sale) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con)
                    throws SQLException {

                PreparedStatement pstmt = con.prepareStatement(
                        "insert into SALE (SALEID,NAME, PRICE, QUANTITY, DATETIME) " + "values (?, ?, ?, ?, ?)", new String[] { "ID" });
                pstmt.setString(1, sale.getSaleId());
                pstmt.setString(2, sale.getName());
                pstmt.setInt(3, sale.getPrice());
                pstmt.setInt(4, sale.getQuantity());
                pstmt.setTimestamp(3, Timestamp.valueOf(sale.getDatetime()));
                return pstmt;
            }
        }, keyHolder);
        Number keyValue = keyHolder.getKey();
        sale.setId(keyValue.longValue());
    }

    public List<Sale> selectBySaleId(String saleId) {
        List<Sale> results = jdbcTemplate.query("select * from SALE where SALEID = ?",
                new RowMapper<Sale>() {
                    @Override
                    public Sale mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Sale sale = new Sale(
                                rs.getString("SALEID"),
                                rs.getString("NAME"),
                                rs.getInt("PRICE"),
                                rs.getInt("QUANTITY"),
                                rs.getTimestamp("DATETIME").toLocalDateTime());
                        sale.setId(rs.getLong("ID"));
                        return sale;
                    }
                }, saleId);
        return results.isEmpty() ? null : results;
    }
    public Sale selectById(Long id) {
        List<Sale> results = jdbcTemplate.query(
                "select * from SALE where ID = ?",
                new RowMapper<Sale>() {
                    @Override
                    public Sale mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Sale sale = new Sale(
                                rs.getString("SALEID"),
                                rs.getString("NAME"),
                                rs.getInt("PRICE"),
                                rs.getInt("QUANTITY"),
                                rs.getTimestamp("DATETIME").toLocalDateTime());
                        sale.setId(rs.getLong("ID"));
                        return sale;
                    }
                }, id);
        return results.isEmpty() ? null : results.get(0);
    }
    public List<Sale> selectAll() {
        List<Sale> results = jdbcTemplate.query("select * from SALE",
                (ResultSet rs, int rowNum) -> {
                    Sale sale = new Sale(
                            rs.getString("SALEID"),
                            rs.getString("NAME"),
                            rs.getInt("PRICE"),
                            rs.getInt("QUANTITY"),
                            rs.getTimestamp("DATETIME").toLocalDateTime());
                    sale.setId(rs.getLong("ID"));
                    return sale;
                });
        return results;
    }
    public void update(Sale sale){
        jdbcTemplate.update("update SALE set NAME = ?, PRICE = ?, QUANTITY = ? where SALEID = ?",
                sale.getName(), sale.getPrice(), sale.getQuantity(),sale.getSaleId());
    }
    public void delete(String outId){
        jdbcTemplate.update(
                "update from ITEM_HISTORY where = OUTID=?",outId);
    }
}
