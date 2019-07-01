package binch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BinchModel {
	Connection con;
	
	public BinchModel() throws Exception{
		con=DBcon.getConnection();
	}
	
	public void insertBinch(Binch bi) throws Exception{
		
		String sql="insert into binch VALUES(?,?,?,?,?,?,?)";
		
		PreparedStatement ps1=con.prepareStatement(sql);
		
		ps1.setInt(1, bi.getBicode());
		ps1.setString(2,bi.getMaker());
		ps1.setString(3,bi.getBinchName());
		ps1.setString(4,bi.getProducer());
		ps1.setString(5,bi.getPrice());
		ps1.setString(6,bi.getExp());
		ps1.setString(7,bi.getImgfname());
		
		System.out.println(ps1);
		ps1.executeUpdate();
		ps1.close();
		
	}

	public ArrayList searchBinch(String str) throws SQLException {
		
		String sql="select * from binch where"
				+ " bname like '%"+str+"%'";
		
		PreparedStatement ps=con.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		ArrayList data=new ArrayList();
		
		while (rs.next()) {
			ArrayList temp=new ArrayList();
			temp.add(rs.getString("bno"));
			temp.add(rs.getString("bmaker"));
			temp.add(rs.getString("bname"));
			temp.add(rs.getString("bprod"));
			temp.add(rs.getString("bprice"));
			data.add(temp);
			
			
		}
		rs.close();
		ps.close();
		
		return data;
	}

	public void modiftBinch(Binch bi) throws Exception {
		String sql="UPDATE binch set bmaker= ?, bname=?,"
				+ " bprod =?, bprice =?, bdetail = ?" + 
				"where bno =?";
		
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, bi.getMaker());
		ps.setString(2, bi.getBinchName());
		ps.setString(3, bi.getProducer());
		ps.setString(4, bi.getPrice());
		ps.setString(5, bi.getExp());
		ps.setInt(6, bi.getBicode());
		
		ps.executeUpdate();
		ps.close();
		
	}
	
	public Binch selectbypk(int no) throws Exception{
		Binch bi=new Binch();
		String sql="select * from binch where bno="+no;
		PreparedStatement ps=con.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while (rs.next()) {
			
			bi.setBicode(Integer.parseInt(rs.getString("bno")));
			bi.setPrice(rs.getString("bprice"));
			bi.setProducer(rs.getString("bprod"));
			bi.setMaker(rs.getString("bmaker"));
			bi.setBinchName(rs.getString("bname"));
			bi.setExp(rs.getString("bdetail"));
			
			bi.setImgfname(rs.getString("imgname"));
			
		}
		rs.close();
		ps.close();
		
		return bi;
		
	}

	public void deleteBinch(Binch bi) throws SQLException {
		String sql="delete binch where bno=?";
		PreparedStatement ps=con.prepareStatement(sql);
		
		ps.setInt(1, bi.getBicode());
		
		ps.executeQuery();
		ps.close();
		
	}
	
	

}
