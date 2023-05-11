package com.example.ebookshop.DB;

import com.example.ebookshop.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBHelper
{
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    private final String DBNAME = "ebookshop";
    private final String USER = "root";
    private final String PASSWORD = "Baiwenbin521..";
    private final String URL = "jdbc:mysql://127.0.0.1:3306/"+DBNAME;

    public static void main(String[] args)
    {
        DBHelper dbHelper = new DBHelper();
        boolean b = dbHelper.AddOrder(new OrderUnit());
        System.out.println(b);
    }

    public boolean Sign(User user)
    {
        try
        {
            GetConnection();
            preparedStatement = connection.prepareStatement("insert into user(account,password) values(?,?)");
            preparedStatement.setString(1, user.getAccount());
            preparedStatement.setString(2, user.getPassword());
            boolean isSucess = preparedStatement.execute();

            return true;

        } catch (Exception exception)
        {
            exception.printStackTrace();
        } finally
        {
            CloseRes();
        }

        return false;

    }


    public List<User> GetUser(User user)
    {
        List<User> users = new ArrayList<>();
        try
        {
            GetConnection();
            preparedStatement = connection.prepareStatement("select * from user where account=?");
            preparedStatement.setString(1,user.getAccount());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                User muser = new User();
                muser.setId(resultSet.getInt("uid"));
                muser.setAccount(resultSet.getString("account"));
                muser.setPassword(resultSet.getString("password"));
                muser.setGender(resultSet.getString("gender"));
                muser.setTelephone(resultSet.getString("telephone"));
                muser.setAddress(resultSet.getString("address"));
                muser.setEmail(resultSet.getString("email"));
                muser.setSigntime(resultSet.getString("signtime"));
                users.add(muser);
            }

        } catch (Exception exception)
        {
            exception.printStackTrace();
        }finally
        {
            CloseRes();
        }

        return users;
    }

    public List<User> GetAllUser()
    {
        List<User> users = new ArrayList<>();
        try
        {
            GetConnection();
            preparedStatement = connection.prepareStatement("select * from user");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                User muser = new User();
                muser.setId(resultSet.getInt("uid"));
                muser.setAccount(resultSet.getString("account"));
                muser.setPassword(resultSet.getString("password"));
                muser.setGender(resultSet.getString("gender"));
                muser.setTelephone(resultSet.getString("telephone"));
                muser.setAddress(resultSet.getString("address"));
                muser.setEmail(resultSet.getString("email"));
                muser.setSigntime(resultSet.getString("signtime"));
                users.add(muser);
            }

        } catch (Exception exception)
        {
            exception.printStackTrace();
        }finally
        {
            CloseRes();
        }

        return users;
    }


    public List<Admin> GetAdmin(Admin admin)
    {
        List<Admin> admins = new ArrayList<>();
        try
        {
            GetConnection();
            preparedStatement = connection.prepareStatement("select * from shopadmin where adminaccount=?");
            preparedStatement.setString(1,admin.getAdminAccount());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                Admin madmin = new Admin();
                madmin.setAid(resultSet.getInt("aid"));
                madmin.setAdminAccount(resultSet.getString("adminaccount"));
                madmin.setAdminPassoWord(resultSet.getString("adminpassword"));
                madmin.setEmail(resultSet.getString("email"));
                admins.add(madmin);
            }

        } catch (Exception exception)
        {
            exception.printStackTrace();
        }finally
        {
            CloseRes();
        }

        return admins;
    }

    public List<User> GetUserByAccount(String account)
    {
        List<User> users = new ArrayList<>();
        try
        {
            GetConnection();
            preparedStatement = connection.prepareStatement("select * from user where account=?");
            preparedStatement.setString(1,account);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                User muser = new User();
                muser.setId(resultSet.getInt("uid"));
                muser.setAccount(resultSet.getString("account"));
                muser.setPassword(resultSet.getString("password"));
                muser.setGender(resultSet.getString("gender"));
                muser.setTelephone(resultSet.getString("telephone"));
                muser.setAddress(resultSet.getString("address"));
                muser.setEmail(resultSet.getString("email"));
                muser.setSigntime(resultSet.getString("signtime"));
                users.add(muser);
            }

        } catch (Exception exception)
        {
            exception.printStackTrace();
        }finally
        {
            CloseRes();
        }

        return users;
    }
    public boolean ModifyUser(User user)
    {
        boolean succ = true;
        try
        {
            GetConnection();
            preparedStatement = connection.prepareStatement("update user set account=?,password=?,gender=?,telephone=?,address=?,email=?,signtime=? where uid=?");
            preparedStatement.setString(1,user.getAccount());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getGender());
            preparedStatement.setString(4,user.getTelephone());
            preparedStatement.setString(5,user.getAddress());
            preparedStatement.setString(6,user.getEmail());
            preparedStatement.setString(7,user.getSigntime());
            preparedStatement.setInt(8,user.getId());
            int i = preparedStatement.executeUpdate();
            if(i!=0)
            {
                System.out.println("[DB]:更新User信息::" + i + "行\n");
                succ = true;
            }else
            {
                System.out.println("[DB]:更新User信息出错");
                succ = false;
            }

        } catch (Exception exception)
        {
            succ = false;
            exception.printStackTrace();
        }finally
        {
            CloseRes();
        }


        return succ;
    }

    public boolean DeleteUser(int uid)
    {
        boolean succ = true;
        try
        {
            GetConnection();
            preparedStatement = connection.prepareStatement("delete from user where uid=?");
            preparedStatement.setInt(1,uid);
            int i = preparedStatement.executeUpdate();
            if(i!=0)
            {
                System.out.println("[DB]:删除User信息::" + i + "行\n");
                succ = true;
            }else
            {
                System.out.println("[DB]:删除User信息出错");
                succ = false;
            }

        } catch (Exception exception)
        {
            succ = false;
            exception.printStackTrace();
        }finally
        {
            CloseRes();
        }


        return succ;
    }

    public List<BookUnit> GetAllBooks()
    {
        List<BookUnit> bookUnits = new ArrayList<>();

        try
        {
            GetConnection();
            preparedStatement = connection.prepareStatement("select * from books");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                BookUnit mbookUnits = new BookUnit();

                mbookUnits.setBno(resultSet.getInt("bno"));
                mbookUnits.setBname(resultSet.getString("bname"));
                mbookUnits.setBauthor(resultSet.getString("bauthor"));
                mbookUnits.setBprice(resultSet.getInt("bprice"));
                mbookUnits.setBcategory(resultSet.getInt("bcategory"));
                mbookUnits.setBurl(resultSet.getString("burl"));
                mbookUnits.setBinfo(resultSet.getString("binfo"));
                mbookUnits.setBpubdate(resultSet.getString("bpubdate"));

                bookUnits.add(mbookUnits);
            }

        } catch (Exception exception)
        {
            exception.printStackTrace();
        }finally
        {
            CloseRes();
        }

        return bookUnits;
    }

    public BookUnit GetBookByBno(int bno)
    {
        BookUnit bookUnit = new BookUnit();

        try
        {
            GetConnection();
            preparedStatement = connection.prepareStatement("select * from books where bno=?");
            preparedStatement.setString(1,bno+"");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                bookUnit.setBno(resultSet.getInt("bno"));
                bookUnit.setBname(resultSet.getString("bname"));
                bookUnit.setBauthor(resultSet.getString("bauthor"));
                bookUnit.setBprice(resultSet.getInt("bprice"));
                bookUnit.setBcategory(resultSet.getInt("bcategory"));
                bookUnit.setBurl(resultSet.getString("burl"));
                bookUnit.setBinfo(resultSet.getString("binfo"));
                bookUnit.setBpubdate(resultSet.getString("bpubdate"));
            }

        } catch (Exception exception)
        {
            exception.printStackTrace();
        }finally
        {
            CloseRes();
        }

        return bookUnit;
    }

    public List<BookUnit> GetACategoryBooks(BookCategory bookCategory)
    {
        List<BookUnit> bookUnits = new ArrayList<>();

        try
        {
            GetConnection();
            preparedStatement = connection.prepareStatement("select * from books where bcategory=?");
            preparedStatement.setString(1,bookCategory.getBcno()+"");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                BookUnit mbookUnits = new BookUnit();

                mbookUnits.setBno(resultSet.getInt("bno"));
                mbookUnits.setBname(resultSet.getString("bname"));
                mbookUnits.setBauthor(resultSet.getString("bauthor"));
                mbookUnits.setBprice(resultSet.getInt("bprice"));
                mbookUnits.setBcategory(resultSet.getInt("bcategory"));
                mbookUnits.setBurl(resultSet.getString("burl"));
                mbookUnits.setBinfo(resultSet.getString("binfo"));
                mbookUnits.setBpubdate(resultSet.getString("bpubdate"));

                bookUnits.add(mbookUnits);
            }

        } catch (Exception exception)
        {
            exception.printStackTrace();
        }finally
        {
            CloseRes();
        }

        return bookUnits;
    }

    public List<BookCategory> GetAllCategorys()
    {
        List<BookCategory> bookCategory = new ArrayList<>();

        try
        {
            GetConnection();
            preparedStatement = connection.prepareStatement("select * from bookcategorys");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                BookCategory mbookCategory = new BookCategory();

                mbookCategory.setBcno(resultSet.getInt("bcno"));
                mbookCategory.setCategoryName(resultSet.getString("cname"));

                bookCategory.add(mbookCategory);
            }

        } catch (Exception exception)
        {
            exception.printStackTrace();
        }finally
        {
            CloseRes();
        }

        return bookCategory;
    }

    public boolean ModifyBookCategory(BookCategory bookCategory)
    {
        boolean succ = true;
        try
        {
            GetConnection();
            preparedStatement = connection.prepareStatement("update bookcategorys set cname=? where bcno=?");
            preparedStatement.setString(1,bookCategory.getCategoryName());
            preparedStatement.setInt(2,bookCategory.getBcno());
            int i = preparedStatement.executeUpdate();
            if(i!=0)
            {
                System.out.println("[DB]:更新BookCategory信息::" + i + "行\n");
                succ = true;
            }else
            {
                System.out.println("[DB]:更新更新BookCategory信息信息出错");
                succ = false;
            }

        } catch (Exception exception)
        {
            succ = false;
            exception.printStackTrace();
        }finally
        {
            CloseRes();
        }


        return succ;
    }

    public boolean DeleteBookCategory(int bcno)
    {
        boolean succ = true;
        try
        {
            GetConnection();
            preparedStatement = connection.prepareStatement("delete from bookcategorys where bcno=?");
            preparedStatement.setInt(1,bcno);
            int i = preparedStatement.executeUpdate();
            if(i!=0)
            {
                System.out.println("[DB]:删除BookCategory信息::" + i + "行\n");
                succ = true;
            }else
            {
                System.out.println("[DB]:删除BookCategory信息出错");
                succ = false;
            }

        } catch (Exception exception)
        {
            succ = false;
            exception.printStackTrace();
        }finally
        {
            CloseRes();
        }


        return succ;
    }

    public boolean AddBookCategory(BookCategory bookCategory)
    {
        boolean succ =true;
        try
        {
            GetConnection();

            preparedStatement = connection.prepareStatement("insert into bookcategorys(cname) values(?)");

            preparedStatement.setString(1,bookCategory.getCategoryName());

            int i = preparedStatement.executeUpdate();
            if(i!=0)
            {
                System.out.println("[DB]:添加BookCategory信息::" + i + "行\n");
                succ = true;
            }else
            {
                System.out.println("[DB]:添加BookCategory信息出错");
                succ = false;
            }

        } catch (Exception exception)
        {
            exception.printStackTrace();
        } finally
        {
            CloseRes();
        }

        return succ;

    }

    public boolean ModifyBook(BookUnit bookUnit)
    {
        boolean succ = true;
        try
        {
            GetConnection();
            preparedStatement = connection.prepareStatement("update books set bname=?,bauthor=?,bprice=?,bcategory=?,burl=?,binfo=?,bpubdate=? where bno=?");
            preparedStatement.setString(1,bookUnit.getBname());
            preparedStatement.setString(2,bookUnit.getBauthor());
            preparedStatement.setInt(3,bookUnit.getBprice());
            preparedStatement.setInt(4,bookUnit.getBcategory());
            preparedStatement.setString(5,bookUnit.getBurl());
            preparedStatement.setString(6,bookUnit.getBinfo());
            preparedStatement.setString(7,bookUnit.getBpubdate());
            preparedStatement.setInt(8,bookUnit.getBno());
            int i = preparedStatement.executeUpdate();
            if(i!=0)
            {
                System.out.println("[DB]:更新Books信息::" + i + "行\n");
                succ = true;
            }else
            {
                System.out.println("[DB]:更新更新Books信息信息出错");
                succ = false;
            }

        } catch (Exception exception)
        {
            succ = false;
            exception.printStackTrace();
        }finally
        {
            CloseRes();
        }


        return succ;
    }

    public boolean DeleteBook(int bno)
    {
        boolean succ = true;
        try
        {
            GetConnection();
            preparedStatement = connection.prepareStatement("delete from books where bno=?");
            preparedStatement.setInt(1,bno);
            int i = preparedStatement.executeUpdate();
            if(i!=0)
            {
                System.out.println("[DB]:删除Book信息::" + i + "行\n");
                succ = true;
            }else
            {
                System.out.println("[DB]:删除Book信息出错");
                succ = false;
            }

        } catch (Exception exception)
        {
            succ = false;
            exception.printStackTrace();
        }finally
        {
            CloseRes();
        }


        return succ;
    }

    public boolean AddBook(BookUnit bookUnit)
    {
        boolean succ =true;
        try
        {
            GetConnection();

            preparedStatement = connection.prepareStatement("insert into books(bname,bauthor,bprice,bcategory,burl,binfo,bpubdate) values(?,?,?,?,?,?,?)");

            preparedStatement.setString(1,bookUnit.getBname());
            preparedStatement.setString(2,bookUnit.getBauthor());
            preparedStatement.setInt(3,bookUnit.getBprice());
            preparedStatement.setInt(4,bookUnit.getBcategory());
            preparedStatement.setString(5,bookUnit.getBurl());
            preparedStatement.setString(6,bookUnit.getBinfo());
            preparedStatement.setString(7,bookUnit.getBpubdate());
            int i = preparedStatement.executeUpdate();
            if(i!=0)
            {
                System.out.println("[DB]:添加Book信息::" + i + "行\n");
                succ = true;
            }else
            {
                System.out.println("[DB]:添加Book信息出错");
                succ = false;
            }

        } catch (Exception exception)
        {
            exception.printStackTrace();
        } finally
        {
            CloseRes();
        }

        return succ;

    }

    public boolean AddOrder(OrderUnit orderUnit)
    {
        boolean succ = true;
        try
        {
            GetConnection();

            preparedStatement = connection.prepareStatement("insert into bookorder(bcfmtime,btotalprice,baddress,btelephone) values(?,?,?,?)");

            preparedStatement.setString(1,orderUnit.getConfirmationtime());
            preparedStatement.setInt(2,orderUnit.getTotalprice());
            preparedStatement.setString(3,orderUnit.getAddress());
            preparedStatement.setString(4,orderUnit.getTelephone());

            int i = preparedStatement.executeUpdate();
            if(i!=0)
            {
                System.out.println("[DB]:添加BookOrder信息::" + i + "行\n");
                succ = true;
            }else
            {
                System.out.println("[DB]:添加BookOrder出错");
                succ = false;
            }

        } catch (Exception exception)
        {
            exception.printStackTrace();
        } finally
        {
            CloseRes();
        }

        return succ;

    }

    public boolean DeleteBookOrder(int boid)
    {
        boolean succ = true;
        try
        {
            GetConnection();
            preparedStatement = connection.prepareStatement("delete from bookorder where boid=?");
            preparedStatement.setInt(1,boid);
            int i = preparedStatement.executeUpdate();
            if(i!=0)
            {
                System.out.println("[DB]:删除BookOrder信息::" + i + "行\n");
                succ = true;
            }else
            {
                System.out.println("[DB]:删除BookOrder信息出错");
                succ = false;
            }

        } catch (Exception exception)
        {
            succ = false;
            exception.printStackTrace();
        }finally
        {
            CloseRes();
        }


        return succ;
    }

    public boolean ModifyBookOrder(OrderUnit orderUnit)
    {
        boolean succ = true;
        try
        {
            GetConnection();
            preparedStatement = connection.prepareStatement("update bookorder set bcfmtime=?,btotalprice=?,baddress=?,btelephone=? where boid=?");
            preparedStatement.setString(1,orderUnit.getConfirmationtime());
            preparedStatement.setInt(2,orderUnit.getTotalprice());
            preparedStatement.setString(3,orderUnit.getAddress());
            preparedStatement.setString(4,orderUnit.getTelephone());
            preparedStatement.setInt(5,orderUnit.getOid());
            int i = preparedStatement.executeUpdate();
            if(i!=0)
            {
                System.out.println("[DB]:更新BookOrder信息::" + i + "行\n");
                succ = true;
            }else
            {
                System.out.println("[DB]:更新更新BookOrder信息信息出错");
                succ = false;
            }

        } catch (Exception exception)
        {
            succ = false;
            exception.printStackTrace();
        }finally
        {
            CloseRes();
        }


        return succ;
    }

    public List<OrderUnit> GetAllBookOrder()
    {
        List<OrderUnit> bookOrders = new ArrayList<>();
        try
        {
            GetConnection();
            preparedStatement = connection.prepareStatement("select * from bookorder");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                OrderUnit morderUnit = new OrderUnit();
                morderUnit.setOid(resultSet.getInt("boid"));
                morderUnit.setConfirmationtime(resultSet.getString("bcfmtime"));
                morderUnit.setTotalprice(resultSet.getInt("btotalprice"));
                morderUnit.setAddress(resultSet.getString("baddress"));
                morderUnit.setTelephone(resultSet.getString("btelephone"));
                bookOrders.add(morderUnit);
            }

        } catch (Exception exception)
        {
            exception.printStackTrace();
        }finally
        {
            CloseRes();
        }

        return bookOrders;
    }

    public boolean AddNotice(NoticeUnit noticeUnit)
    {
        boolean succ = true;
        try
        {
            GetConnection();

            preparedStatement = connection.prepareStatement("insert into shopnotice(noticetitle,noticedate,noticecontent) values(?,?,?)");

            preparedStatement.setString(1,noticeUnit.getNoticeTitle());
            preparedStatement.setString(2,noticeUnit.getNoticeDate());
            preparedStatement.setString(3,noticeUnit.getNoticeContent());

            int i = preparedStatement.executeUpdate();
            if(i!=0)
            {
                System.out.println("[DB]:添加Nitice信息::" + i + "行\n");
                succ = true;
            }else
            {
                System.out.println("[DB]:添加Nitice出错");
                succ = false;
            }

        } catch (Exception exception)
        {
            exception.printStackTrace();
        } finally
        {
            CloseRes();
        }

        return succ;

    }

    public boolean DeleteNotice(int nid)
    {
        boolean succ = true;
        try
        {
            GetConnection();
            preparedStatement = connection.prepareStatement("delete from shopnotice where nid=?");
            preparedStatement.setInt(1,nid);
            int i = preparedStatement.executeUpdate();
            if(i!=0)
            {
                System.out.println("[DB]:删除Notice信息::" + i + "行\n");
                succ = true;
            }else
            {
                System.out.println("[DB]:删除Notice信息出错");
                succ = false;
            }

        } catch (Exception exception)
        {
            succ = false;
            exception.printStackTrace();
        }finally
        {
            CloseRes();
        }


        return succ;
    }

    public boolean ModifyNotice(NoticeUnit noticeUnit)
    {
        boolean succ = true;
        try
        {
            GetConnection();
            preparedStatement = connection.prepareStatement("update shopnotice set noticetitle=?,noticedate=?,noticecontent=? where nid=?");
            preparedStatement.setString(1,noticeUnit.getNoticeTitle());
            preparedStatement.setString(2,noticeUnit.getNoticeDate());
            preparedStatement.setString(3,noticeUnit.getNoticeContent());
            preparedStatement.setInt(4,noticeUnit.getNid());
            int i = preparedStatement.executeUpdate();
            if(i!=0)
            {
                System.out.println("[DB]:更新Notice信息::" + i + "行\n");
                succ = true;
            }else
            {
                System.out.println("[DB]:更新更新Notice信息信息出错");
                succ = false;
            }

        } catch (Exception exception)
        {
            succ = false;
            exception.printStackTrace();
        }finally
        {
            CloseRes();
        }


        return succ;
    }

    public List<NoticeUnit> GetAllNotice()
    {
        List<NoticeUnit> noticeUnits = new ArrayList<>();

        try
        {
            GetConnection();
            preparedStatement = connection.prepareStatement("select * from shopnotice");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                NoticeUnit mnoticeUnits = new NoticeUnit();
                mnoticeUnits.setNid(resultSet.getInt("nid"));
                mnoticeUnits.setNoticeTitle(resultSet.getString("noticetitle"));
                mnoticeUnits.setNoticeDate(resultSet.getString("noticedate"));
                mnoticeUnits.setNoticeContent(resultSet.getString("noticecontent"));
                noticeUnits.add(mnoticeUnits);
            }

        } catch (Exception exception)
        {
            exception.printStackTrace();
        }finally
        {
            CloseRes();
        }

        return noticeUnits;
    }
    private void GetConnection() throws SQLException
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(this.URL, this.USER, this.PASSWORD);
            System.out.println("[DB]::获取连接成功");
        } catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }
    private void CloseRes()
    {
        try
        {
            if(resultSet!=null){
                resultSet.close();
            }
            if (preparedStatement != null)
            {
                preparedStatement.close();
            }
            if (connection != null)
            {
                connection.close();
            }
            System.out.println("[DB]::释放资源成功");
        } catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

}
