package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.sql.ResultSet;
import common.JdbcUtil;

public class BoardDao {
	private JdbcUtil ju;
	
	public BoardDao(){
		ju=JdbcUtil.getInstance();
	}

	//삽입(C)
	public int insert(BoardVo vo){
		Connection con=null;
		PreparedStatement pstmt=null;
		String query="insert into board(num,title,writer,content,regdate,cnt) values(0,?,?,?,sysdate(),0)";
		int res=-1;
		try{
			con=ju.getConnection();
			pstmt=con.prepareStatement(query);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getWriter());
			pstmt.setString(3, vo.getContent());
			res=pstmt.executeUpdate();

		}catch(SQLException e){
			System.out.println("insert문 오류:"+e);
		}finally{
				if(pstmt!=null){
					try {
						pstmt.close(); //풀에 반환
					} catch (SQLException e) {
						e.printStackTrace();
					} 
				}//if-end
				if(con!=null){
					try {
						con.close(); //풀에 반환
					} catch (SQLException e) {
						e.printStackTrace();
					} 
				}//if-end
			}//finally-end
			return res;
		}//insert()-end

		//조회(R)
		public List<BoardVo> selectAll(){
			Connection con=null;
			Statement stmt=null;
			ResultSet rs=null;
			String query="select * from board order by num desc";
			ArrayList<BoardVo> ls=new ArrayList<BoardVo>();
			try{
				con=ju.getConnection();
				stmt=con.createStatement();
				rs=stmt.executeQuery(query);

				while(rs.next()){
					BoardVo vo=new BoardVo(
							rs.getInt(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4),
							new Date(rs.getDate(5).getTime()),
							rs.getInt(6));
					ls.add(vo);

				}//while-end
			}catch(SQLException e){
				System.out.println("select문 예외:"+e);
			}finally{
				if(rs!=null){
					try {
						rs.close(); //풀에 반환
					} catch (SQLException e) {
						e.printStackTrace();
					} 
				}//if-end
				if(stmt!=null){
					try {
						stmt.close(); //풀에 반환
					} catch (SQLException e) {
						e.printStackTrace();
					} 
				}//if-end
				if(con!=null){
					try {
						con.close(); //풀에 반환
					} catch (SQLException e) {
						e.printStackTrace();
					} 
				}//if-end
			}//finally-end
			return ls;
		}//selectAll()-end
		
		//조회(R)
				public BoardVo selectOne(int num){
					Connection con=null;
					PreparedStatement pstmt=null;
					ResultSet rs=null;
					String query="select * from board where num=?";
					BoardVo vo=null;
					try{
						con=ju.getConnection();
						pstmt=con.prepareStatement(query);
						pstmt.setInt(1, num);
						rs=pstmt.executeQuery();

						if(rs.next()){
							vo=new BoardVo(
									rs.getInt(1),
									rs.getString(2),
									rs.getString(3),
									rs.getString(4),
									new Date(rs.getDate(5).getTime()),
									rs.getInt(6));
						}//while-end
					}catch(SQLException e){
						System.out.println("select문 예외:"+e);
					}finally{
						if(rs!=null){
							try {
								rs.close(); //풀에 반환
							} catch (SQLException e) {
								e.printStackTrace();
							} 
						}//if-end
						if(pstmt!=null){
							try {
								pstmt.close(); //풀에 반환
							} catch (SQLException e) {
								e.printStackTrace();
							} 
						}//if-end
						if(con!=null){
							try {
								con.close(); //풀에 반환
							} catch (SQLException e) {
								e.printStackTrace();
							} 
						}//if-end
					}//finally-end
					return vo;
				}//selectAll()-end
		
		//수정(U)

		//삭제(D)
	}
