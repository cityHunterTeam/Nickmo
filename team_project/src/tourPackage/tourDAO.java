package tourPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class tourDAO {
		Connection con;
		PreparedStatement pstmt;
		ResultSet rs;
		
		//커넥션풀(DataSource)을 얻은 후 ConnecionDB접속
			private Connection getConnection() throws Exception{
				Context init = new InitialContext();
				DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/travel");
				//커넥션풀에 존재하는 커넥션 얻기
				Connection con = ds.getConnection();
				//커넥션 반환12
				return con;
			}
			
			private void freeResource() {
				try {
					if(rs == null) {rs.close();}
					if(pstmt != null) {pstmt.close();}
					if(con != null) {con.close();}
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		
			public int getTourCount() {//전체글개수
				
				String sql="";
				int count=0;
				try {
					con=getConnection();
					sql="select count(*) from tour";
					pstmt=con.prepareStatement(sql);
					rs=pstmt.executeQuery();
					
					if(rs.next()) {
						count=rs.getInt(1);
					}
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					freeResource();
				}
				return count;
			}
			
			public List<tourVO> getTourList(int startRow, int pageSize){
				String sql = "";
				List<tourVO> tourList = new ArrayList<tourVO>();
				
				try {
					//DB연결 
					
					con = getConnection();
					//SQL문 만들기 
					//정렬 re_ref 내림차순 정렬하여 검색한 후 re_seq 오름차순정렬하여 검색해 오는데 
					//limit 각 페이지마다 맨위에 첫번째로 보여질 시작글 번호, 한 페이지당 보여줄 글개수 
					sql = "select * from tour order by num desc limit ?,?";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, startRow);
					pstmt.setInt(2, pageSize);
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						tourVO vo = new tourVO();
						//rs=> Boardvo에 저장 
						vo.setId(rs.getString("id"));
						vo.setNum(rs.getInt("num"));
						vo.setImage(rs.getString("image"));
						vo.setTitle(rs.getString("title"));
						vo.setContent(rs.getString("content"));
						vo.setDate(rs.getTimestamp("date"));
						vo.setPos(rs.getInt("pos"));
						vo.setDept(rs.getInt("dept"));
						vo.setReadcount(rs.getInt("readcount"));
						 //Boardvo => ArrayList에 추가 
						 
						tourList.add(vo);
					}//while반복
				}catch (Exception e) {
					System.out.println("getTourList메소드에서 예외발생 : " + e);
					// TODO: handle exception
				}finally {
					freeResource();
				}
				return tourList; //ArrayList를 notice.jsp로 리턴 
			}//getBoardList메소드 끝 
			
			
						
			public int insertTour(tourVO vo) {
				int num =0;
				try {
					con = getConnection();
					String title = vo.getTitle();
					String content = vo.getContent();
					String id = vo.getId();
					String image = vo.getImage();
					String query = "INSERT INTO tour (id,image,title,content,date,readcount)"
							+ " VALUES (?, ? ,?, ?,now(),0)";
					System.out.println(query);
					pstmt = con.prepareStatement(query);
					
					pstmt.setString(1, id);
					pstmt.setString(2, image);
					pstmt.setString(3, title);
					pstmt.setString(4, content);
					
					pstmt.executeUpdate();
					
					query = "SELECT  max(num) from tour ";
					System.out.println(query);
					pstmt = con.prepareStatement(query);
					ResultSet rs = pstmt.executeQuery(query);
					if (rs.next())
						num = rs.getInt(1);
					pstmt.close();
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

				return num;
			}
			
			public void tourReadCount(int num) { //조회수
				
				String sql="";
			
				try {
					con=getConnection();
					sql="update tour set readcount=readcount+1 where num=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1, num);
					pstmt.executeUpdate();
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					if(rs!=null)try {rs.close();}catch(SQLException ex) {}
					if(pstmt!=null)try {pstmt.close();}catch(SQLException ex) {}
					if(con!=null)try {con.close();}catch(SQLException ex) {}
				}
				
			}
			
			public tourVO getupload(int num){ //글 상세보기
				
				tourVO vo = null;		
				try {
					
					con = getConnection();
					
					String sql = "select * from tour where num=?";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, num);			
					rs = pstmt.executeQuery();  			
					if(rs.next()) {			
					vo = new tourVO();
					vo.setNum(rs.getInt("num"));
					vo.setId(rs.getString("id"));
					vo.setImage(rs.getString("image"));
					vo.setTitle(rs.getString("title"));
				 	vo.setContent(rs.getString("content"));
					vo.setReadcount(rs.getInt("readcount"));
					vo.setDate(rs.getTimestamp("date"));
					
					}
				} catch (Exception e) {
					System.out.println("getupload 얻기 실패 : "+e);
				} finally {
					freeResource();
					if(con != null){try {con.close();} catch (Exception e) {e.printStackTrace();}}
					if(rs != null){try {rs.close();} catch (Exception e) {e.printStackTrace();}}
					if(pstmt != null){try {pstmt.close();} catch (Exception e) {e.printStackTrace();}}
				}//finally					
				return vo; //getupload 끝
			}
			
			public void updateTour(tourVO vo,int num,int chk) { //글수정
				String sql ="";
				
				try {
						con=getConnection();
		
					if (chk == 1) {
						sql = "update tour set title=?,content=?,image=? where num=?";
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, vo.getTitle());
						pstmt.setString(2, vo.getContent());
						pstmt.setString(3, vo.getImage());
						pstmt.setInt(4, num);
						pstmt.executeUpdate();
					} else if (chk == 0) {
						sql = "update tour set title=?,content=? where num=?";
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, vo.getTitle());
						pstmt.setString(2, vo.getContent());
						pstmt.setInt(3, num);
						pstmt.executeUpdate();
					}
				}catch(Exception e) {
					e.printStackTrace();
				}finally{
					if(rs!=null)try {rs.close();}catch(SQLException ex) {}
					if(pstmt!=null)try {pstmt.close();}catch(SQLException ex) {}
					if(con!=null)try {con.close();}catch(SQLException ex) {}
				}
			}
			
			public void deleteTour(tourVO vo,int num) {
				
				String sql = "";
				try {
					con = getConnection();
					
			
					sql = "delete from tour where num=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, vo.getNum());
					pstmt.executeUpdate();
						
					
				} catch (Exception e) {
					System.out.println("deleteReview메서드에서 예외발생 : " + e);
				}finally {
					if(rs!=null)try {rs.close();}catch(SQLException ex) {}
					if(pstmt!=null)try {pstmt.close();}catch(SQLException ex) {}
					if(con!=null)try {con.close();}catch(SQLException ex) {}
				}			
			}
			
			
}
