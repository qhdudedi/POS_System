package springfinal.pos.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import springfinal.pos.domain.SaleItem;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Collections;
import java.util.List;


public class StaticDao {
    final private JdbcTemplate jdbcTemplate;

    @Autowired
    public StaticDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**최다 판매 제품 */
    public List<SaleItem> getMaxSoldProducts() {
        String sql = "SELECT SALEID, PRICE, QUANTITY, DATETIME " +
                "FROM SALE " +
                "ORDER BY QUANTITY DESC " +
                "LIMIT 3";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
                SaleItem saleItem = new SaleItem();
                saleItem.setSaleId(rs.getString("SALEID"));
                saleItem.setPrice(rs.getInt("PRICE"));
                saleItem.setQuantity(rs.getInt("QUANTITY"));
                return saleItem;
        });
    }
    /**하루 판매금액 */
    public int getDailySales(LocalDate date) {
        String sql = "SELECT SUM(PRICE * QUANTITY) " +
                "FROM SALE " +
                "WHERE DATE(DATETIME) = ?";

        return jdbcTemplate.queryForObject(sql, Integer.class, date);
    }
    /**일주일 판매 금액*/
    public int getWeeklySales(LocalDate startDate){
        LocalDate endDate = startDate.plusDays(6);
        String sql ="SELECT SUM(PRICE * QUANTITY) " +
                "FROM SALE " +
                "WHERE DATETIME >= ? AND DATETIME <= ?";
        return jdbcTemplate.queryForObject(sql,Integer.class, startDate, endDate);
    }
    /**한 달 판매금액 */
    public int getMonthlySales(YearMonth yearMonth){
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();
        String sql = "SELECT SUM(price*quantity) " +
                "FROM SALE " +
                "WHERE DATETIME >= ? AND DATETIME <= ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, startDate,endDate);
    }
    public List<SaleItem> getSalesInfoByDay(LocalDate date) {
        String sql = "SELECT SALEID, PRICE, QUANTITY, DATETIME FROM SALE WHERE DATE(DATETIME) = ?";
        return jdbcTemplate.query(sql, new SaleItemMapper(), date);
    }

    public int getTotalSalesByDate(LocalDate date) {
        String sql = "SELECT SUM(QUANTITY) FROM SALE WHERE DATE(DATETIME) = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, date);
    }

    public List<SaleItem> getSalesInfoByWeekly(LocalDate startDate) {
        LocalDate endDate = startDate.plusDays(6);
        String sql = "SELECT SALEID, PRICE, QUANTITY, DATETIME " +
                "FROM SALE " +
                "WHERE DATE(DATETIME) BETWEEN ? AND ?";
        try {
            return jdbcTemplate.query(sql, new SaleItemMapper(), startDate, endDate);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public int getTotalSalesByWeekly(LocalDate startDate) {
        LocalDate endDate = startDate.plusDays(6);
        String sql = "SELECT SUM(QUANTITY) " +
                "FROM SALE " +
                "WHERE DATE(DATETIME) BETWEEN ? AND ?";
        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, startDate, endDate);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<SaleItem> getSalesByMonth(YearMonth yearMonth) {
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();
        String sql = "SELECT SALEID, PRICE, QUANTITY, DATETIME " +
                "FROM SALE " +
                "WHERE DATE(DATETIME) BETWEEN ? AND ?";
        try {
            return jdbcTemplate.query(sql, new SaleItemMapper(), startDate, endDate);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public int getTotalSalesByMonth(YearMonth yearMonth) {
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();
        String sql = "SELECT SUM(QUANTITY) " +
                "FROM SALE " +
                "WHERE DATE(DATETIME) BETWEEN ? AND ?";
        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, startDate, endDate);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return 0;
        }
    }
    private static class SaleItemMapper implements RowMapper<SaleItem> {
        @Override
        public SaleItem mapRow(ResultSet rs, int rowNum) throws SQLException {
            try {
                SaleItem item = new SaleItem();
                item.setSaleId(rs.getString("SALEID"));
                item.setPrice(rs.getInt("PRICE"));
                item.setQuantity(rs.getInt("QUANTITY"));
                item.setDateTime(rs.getTimestamp("DATETIME").toLocalDateTime());
                return item;
            } catch (SQLException e) {
                throw new DataAccessException("Error occurred while mapping ResultSet", e) {
                };
            }
        }
    }
}

