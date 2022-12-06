package com.lxh.ATMSystem;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

//ATM系统首页

public class ATM_System {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<account> accounts = new ArrayList<>();
        while (true) {
            System.out.println("---------------ATM系统-----------------");
            System.out.println("1.账户登录");
            System.out.println("2.账户注册");
            System.out.println("选择你的操作:");
            int input = scan.nextInt();
            switch (input) {
                case 1:
                    //账户登录
                    login(accounts, scan);
                    break;
                case 2:
                    //账户注册
                    register(accounts);
                    break;
                default:
                    System.out.println("输入操作不存在!");
            }
        }

    }

    /**
     * 登录功能
     *
     * @param accounts 账户集合
     * @param scan     扫描器
     */
    private static void login(ArrayList<account> accounts, Scanner scan) {
        if (accounts.size() == 0) {
            System.out.println("系统中无账户");
            return; //结束方法的执行
        }
        System.out.println("=================欢迎登录================");
        while (true) {
            System.out.println("请输入卡号:");
            String cardId = scan.next();
            account acc = getcardById(cardId, accounts);
            if (acc != null) {
                for (int i = 1; i <= 3; i++) {
                    System.out.println("请输入密码:");
                    String password = scan.next();
                    if (password.equals(acc.getPassword())) {
                        System.out.println("登录成功!");
                        //登陆后的操作页面
                        showUserConmmand(scan, acc, accounts);
                        return;


                    } else {
                        System.out.println("密码错误,还剩" + (3 - i) + "次");
                    }
                }
                break;
            } else {
                System.out.println("账户不存在!");
            }
        }
//82836501 99067444
    }

    /**
     * 展示用户成功登陆后的操作页面
     */
    private static void showUserConmmand(Scanner scan, account acc, ArrayList<account> accounts) {
        while (true) {
            System.out.println("=================用户操作页面=================");
            System.out.println("1.查询账户");
            System.out.println("2.存款");
            System.out.println("3.取款");
            System.out.println("4.转账");
            System.out.println("5.修改密码");
            System.out.println("6.退出");
            System.out.println("7.注销账户");
            System.out.println("请选择操作:");
            int conmmand = scan.nextInt();
            switch (conmmand) {
                case 1:
                    //查询账户
                    search(acc);
                    break;
                case 2:
                    //存款
                    deposit(acc, scan);
                    break;
                case 3:
                    //取款
                    withDraw(acc, scan);
                    break;
                case 4:
                    //转账
                    transfer(scan, acc, accounts);
                    break;
                case 5:
                    //修改密码
                    changePassword(acc, scan);
                    break;
                case 6:
                    //退出
                    System.out.println("退出成功");
                    return;
                case 7:
                    //注销账户
                    logout(scan, accounts);
                    break;
                default:
                    System.out.println("你输入的操作不存在");
            }
        }
    }

    /**
     * 注销功能
     *
     * @param scan
     * @param accounts
     */
    private static void logout(Scanner scan, ArrayList<account> accounts) {
        while (true) {
            System.out.println("请输入注销卡号:");
            String cardID = scan.next();
            account aimacc = getcardById(cardID, accounts);
            if (aimacc == null) {
                System.out.println("账户不存在,请重新输入");
            } else {
                accounts.remove(aimacc);
                System.out.println("注销成功!h");
                break;
            }
        }

    }

    /**
     * 转账功能
     *
     * @param scan
     * @param acc      当前账户
     * @param accounts 账户集合
     */
    private static void transfer(Scanner scan, account acc, ArrayList<account> accounts) {

        System.out.println("输入需要转入的账户卡号:");
        String aimCardId = scan.next();
        account aimacc = getcardById(aimCardId, accounts);
        if (aimacc == null) {
            System.out.println("该账户不存在!");
            return;
        }
//        for (int i = 0; i < accounts.size(); i++) {
//            account account = accounts.get(i);
//            if (account.getCard_Id().equals(aimCardId)) {
//                account aimacc = account;
//                break;
//            } else {
//                System.out.println("该账户不存在!");
//                return;
//            }
//        }

        while (true) {
            System.out.println("输入转账金额:");
            double money = scan.nextDouble();
            if (money > acc.getMoney()) {
                System.out.println("余额不足,请重新输入");
            } else {
                acc.setMoney(acc.getMoney() - money);
                aimacc.setMoney(aimacc.getMoney() + money);
                break;
            }
        }
        System.out.println("当前余额为:" + acc.getMoney());
        System.out.println("对方余额为:" + aimacc.getMoney());

    }

    /**
     * 修改密码功能
     *
     * @param acc  修改密码账户
     * @param scan 扫描器
     */
    private static void changePassword(account acc, Scanner scan) {
        while (true) {
            System.out.println("请输入原密码:");
            String pswd = scan.next();
            if (pswd.equals(acc.getPassword())) {
                while (true) {
                    System.out.println("请输入新密码:");
                    String newPassWord = scan.next();
                    System.out.println("确认新密码:");
                    String newPassWord2 = scan.next();
                    if (newPassWord.equals(newPassWord2)) {
                        acc.setPassword(newPassWord);
                        break;
                    } else {
                        System.out.println("两次密码不一致");
                    }
                }
                break;
            } else {
                System.out.println("密码错误请重新输入:");
            }
        }
    }

    /**
     * 取钱功能
     *
     * @param acc  取钱账户
     * @param scan
     */
    private static void withDraw(account acc, Scanner scan) {
        while (true) {
            System.out.println("请输入你要取出的金额:");
            double money = scan.nextDouble();
            if (money <= acc.getQuote_money() && money <= acc.getMoney()) {
                System.out.println("取出金额:" + money);
                acc.setMoney(acc.getMoney() - money);
                System.out.println("取出成功");
                System.out.println("当前余额为:" + acc.getMoney());
                break;
            } else {
                System.out.println("超出限额或者余额不足,请重新输入金额");
            }
        }
    }

    /**
     * 存钱功能 02221976 27373454
     *
     * @param acc 存储账户
     */
    private static void deposit(account acc, Scanner scan) {
        System.out.println("请输入你要存入的金额:");
        double money = scan.nextDouble();
        System.out.println("存入金额:" + money);
        acc.setMoney(acc.getMoney() + money);
        System.out.println("存入成功");
        System.out.println("当前余额为:" + acc.getMoney());
    }

    /**
     * 查询账户功能
     *
     * @param acc 带查询账户
     */
    private static void search(account acc) {
        System.out.println("===============当前账户信息如下==============");
        System.out.println("卡号:" + acc.getCard_Id());
        System.out.println("用户名:" + acc.getUser_Name());
        System.out.println("余额:" + acc.getMoney());
        System.out.println("限额:" + acc.getQuote_money());
        return;
    }

    /**
     * 注册账户功能
     *
     * @param accounts //接受账户集合
     */
    private static void register(ArrayList<account> accounts) {
        System.out.println("=================注册账户================");
        account account = new account();   //创建一个账户对象
        System.out.println("请输入用户名:");
        Scanner scan = new Scanner(System.in);
        String userName = scan.next();
        account.setUser_Name(userName);     //1.录入用户名


        System.out.println("请输入密码:");           //2.设置密码
        String password = scan.next();
        while (true) {
            System.out.println("请确认密码:");
            String okPassword = scan.next();
            if (password.equals(okPassword)) {
                account.setPassword(password);
                break;

            } else {
                System.out.println("两次密码不一致!");

            }
        }

        System.out.println("请输入限额:");       //3.设置限额
        double quoteMoney = scan.nextDouble();
        account.setQuote_money(quoteMoney);

        String cardId = getcardId(accounts); //4. 设置卡号
        account.setCard_Id(cardId);

        accounts.add(account);
        System.out.println("恭喜你" + userName + ",账户注册成功!你的卡号是" + cardId);
    }

    /**
     * 卡号生成
     *
     * @param accounts
     * @return
     */
    private static String getcardId(ArrayList<account> accounts) {
        Random random = new Random();
        while (true) {
            String cardId = "";

            for (int i = 0; i < 8; i++) {
                cardId += random.nextInt(10);
            }
            // 判断卡号不能重复
            account acc = getcardById(cardId, accounts);
            if (acc == null) {
                return cardId;
            }

        }
    }

    /**
     * 通过id找账户
     *
     * @param CardId   根据的id
     * @param accounts 账户集合
     * @return 返回的账户 / null
     */
    private static account getcardById(String CardId, ArrayList<account> accounts) {
        for (int i = 0; i < accounts.size(); i++) {
            account acc = accounts.get(i);
            if (CardId.equals(acc.getCard_Id())) {
                return acc;
            }

        }
        return null;
    }
}
