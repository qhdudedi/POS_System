package springfinal.pos.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import springfinal.pos.domain.Item;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class ItemDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ItemDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * 상품 등록
     */
    public void read(Item item) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                        PreparedStatement pstmt = con.prepareStatement("insert into ITEM (ITEMID, NAME, PRICE, REGDATE, QUANTITY) values (?, ?, ?, ?,?)", new String[]{"ID"});
                        pstmt.setString(1, item.getItemId());
                        pstmt.setString(2, item.getName());
                        pstmt.setInt(3, item.getPrice());
                        pstmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
                        pstmt.setInt(5, item.getQuantity());
                        return pstmt;
                    }
                },
                keyHolder);
        Number keyValue = keyHolder.getKey();
        item.setId(keyValue.longValue());

    }

    /**상품 조회*/
    public Item selectById(Long id) {
        List<Item> results = jdbcTemplate.query("select * from item where ID = ?",
                new RowMapper<Item>() {
                    @Override
                    public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Item item = new Item(
                                rs.getString("ITEMID"),
                                rs.getString("NAME"),
                                rs.getInt("PRICE"),
                                rs.getTimestamp("REGDATE"),
                                rs.getInt("QUANTITY"));
                        item.setId(rs.getLong("ID"));
                        return item;
                    }
                }, id);
        return results.isEmpty() ? null : results.get(0);
    }

    public Item selectByItemId(String itemId) {
        List<Item> results = jdbcTemplate.query("select * from item where ITEMID = ?",
                new RowMapper<Item>() {
                    @Override
                    public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Item item = new Item(
                                rs.getString("ITEMID"),
                                rs.getString("NAME"),
                                rs.getInt("PRICE"),
                                rs.getTimestamp("REGDATE"),
                                rs.getInt("QUANTITY"));
                        item.setId(rs.getLong("ID"));
                        return item;
                    }
                }, itemId);
        return results.isEmpty() ? null : results.get(0);
    }
    public List<Item> findByItemId(String itemId) {
        List<Item> results = jdbcTemplate.query("select * from item where ITEMID = ?",
                new RowMapper<Item>() {
                    @Override
                    public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Item item = new Item(
                                rs.getString("ITEMID"),
                                rs.getString("NAME"),
                                rs.getInt("PRICE"),
                                rs.getTimestamp("REGDATE"),
                                rs.getInt("QUANTITY"));
                        item.setId(rs.getLong("ID"));
                        return item;
                    }
                }, itemId);
        return results.isEmpty() ? null : results;
    }

    public List<Item> selectAll() {
        List<Item> results = jdbcTemplate.query("select * from item ",
                new RowMapper<Item>() {
                    @Override
                    public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Item item = new Item(
                                rs.getString("ITEMID"),
                                rs.getString("NAME"),
                                rs.getInt("PRICE"),
                                rs.getTimestamp("REGDATE"),
                                rs.getInt("QUANTITY"));
                        item.setId(rs.getLong("ID"));
                        return item;
                    }
                });
        return results;
    }

    /**상품 업데이트*/
    public void update(Item item) {
        jdbcTemplate.update("update ITEM set NAME = ?, PRICE = ?, REGDATE =?,  QUANTITY = ? where ITEMID = ?",
                item.getName(), item.getPrice(), item.getRegisterDate(), item.getQuantity(), item.getItemId());
    }
    public void edit(Item item){
        jdbcTemplate.update("update ITEM set NAME = ?, PRICE = ?, REGDATE =?,  QUANTITY = ? where ITEMID = ?",
                item.getName(), item.getPrice(), item.getRegisterDate(), item.getQuantity(), item.getItemId());
    }
    public void add(Item item) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                        PreparedStatement pstmt = con.prepareStatement("insert into SALE (SALEID, NAME, PRICE, DATETIME, QUANTITY) values (?, ?, ?, ?,?)", new String[]{"ID"});
                        pstmt.setString(1, item.getItemId());
                        pstmt.setString(2, item.getName());
                        pstmt.setInt(3, item.getPrice());
                        pstmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
                        pstmt.setInt(5, item.getQuantity());
                        return pstmt;
                    }
                },
                keyHolder);
        Number keyValue = keyHolder.getKey();
        item.setId(keyValue.longValue());

    }

}
