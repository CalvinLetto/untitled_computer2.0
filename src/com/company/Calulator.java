package com.company;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.*;

public class Calulator extends JFrame {
    // 北部
    JPanel p1 = new JPanel();
    // 文本框
    JTextField t = new JTextField("");

    // 南部
    JPanel p2 = new JPanel();
    JButton b[] = new JButton[28];

    public void init() {
        b[0] = new JButton("1");
        b[1] = new JButton("2");
        b[2] = new JButton("3");
        b[3] = new JButton("+");
        b[4] = new JButton("4");
        b[5] = new JButton("5");
        b[6] = new JButton("6");
        b[7] = new JButton("-");
        b[8] = new JButton("7");
        b[9] = new JButton("8");
        b[10] = new JButton("9");
        b[11] = new JButton("*");
        b[12] = new JButton(".");
        b[13] = new JButton("0");
        b[14] = new JButton("=");
        b[15] = new JButton("/");
        b[16] = new JButton("(");
        b[17] = new JButton(")");
        b[18] = new JButton("Back");
        b[19] = new JButton("C");
        b[20] = new JButton("s");
        b[21] = new JButton("c");
        b[22] = new JButton("t");
        b[23] = new JButton("a");
        b[24] = new JButton("负号");
        b[25] = new JButton("根号");
        b[26] = new JButton("倒数");
        b[27] = new JButton("%");
    }

    public Calulator() {
        this.setTitle("计算器");
        // 设置窗体不能改变
        this.setResizable(false);
        // 设置页面出现位置
        this.setBounds(40, 45, 400, 350);
        // p1添加在窗口上
        this.add(p1, "North");
        // 文本框设置在p1上
        p1.add(t);
        // 设置文本框宽度
        t.setColumns(27);
        // 设置文本内容位置
        t.setHorizontalAlignment(JTextField.RIGHT);
        // 设置文本框不可编辑
        //t.setEditable(false);
        // 设置网格布局6*4
        GridLayout g1 = new GridLayout(7, 4);
        // 设置水平距离
        g1.setHgap(10);
        // 设置垂直距离
        g1.setVgap(10);
        p2.setLayout(g1);
        // 把p2添加到窗口中
        this.add(p2);
        // 初始化按钮
        init();
        for (int i = 0; i < 28; i++) {
            p2.add(b[i]);
            b[i].addActionListener(new Event(this));
        }

        // 西部
        // 创建一个标签
        JLabel jb1 = new JLabel();
        // 设置标签的面积
        jb1.setPreferredSize(new Dimension(10, 0));
        this.add(jb1, "West");

        // 东部
        // 创建一个标签
        JLabel jb2 = new JLabel();
        // 设置标签的面积
        jb2.setPreferredSize(new Dimension(10, 0));
        this.add(jb2, "East");


        this.setVisible(true);
        // 设置窗体关闭时进程关闭
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    class Event implements ActionListener {
        Calulator evd;
        Event(Calulator evd) {
            this.evd=evd;
        }
        public void actionPerformed(ActionEvent e) {
            String str=evd.t.getText();
            for(int i=0;i<28;i++) {
                if(e.getSource()==evd.b[i]) {

                    //=号
                    if(i==14) {
                        Calculate c=new Calculate(str);
                        c.transform();
                        if(c.value().equals("error"))
                            evd.t.setText("");
                        else
                            evd.t.setText(c.value());
                    }
                    else if(i==25){
                        str=str+"S";
                        evd.t.setText(str);
                    }
                    else if(i==26){
                        str=str+"R";
                        evd.t.setText(str);
                    }
                    else if(i==24){
                        str=str+"m";
                        evd.t.setText(str);
                    }
                    //删除号
                    else if(i==18) {
                        if(evd.t.getText().length()>0)
                            evd.t.setText(str.substring(0, str.length()-1));
                    }
                    //C  清空
                    else if(i==19)
                        evd.t.setText("");
                    else
                        evd.t.setText(str+evd.b[i].getText());
                }
            }
        }
    }


    class WarningGUI extends JFrame{
        JLabel label;
        WarningGUI (String text){
            this.setTitle("Warning");
            this.setSize(200,80);
            label=new JLabel(text);
            //设置标签内字体类型
            label.setFont(new Font(Font.DIALOG,Font.PLAIN,25));
            //设置窗口的布局管理
            this.setLayout(new FlowLayout());
            //设置窗口出现在正中央
            this.setLocationRelativeTo(null);
            //不可改变窗口大小
            this.setResizable(false);
            this.add(label);
            //显示出来
            this.setVisible(true);
            //关闭时退出进程
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }

    class Stack {
        char []s=new char [20];
        int length;
        Stack() {
            this.length=0;
        }
        //将元素推入栈中
        void push(char c) {
            s[length]=c;
            length++;
        }
        //取出栈顶元素，并删除
        char pop() {
            this.length--;
            return s[this.length];
        }
        //获取栈顶元素
        char getTop() {
            return s[this.length-1];
        }
    }

    class StackPro {
        float []s=new float [24];
        int length;
        StackPro() {
            this.length=0;
        }
        void push(float c) {
            s[length]=c;
            length++;
        }
        float pop() {
            this.length--;
            return s[this.length];
        }
        float getTop() {
            return s[this.length-1];
        }
    }


    @SuppressWarnings("serial")
    class DivisorException extends Exception {
        DivisorException() {
            super("除数不能为0");
        }
    }
    @SuppressWarnings("serial")
    class DivisorException1 extends Exception {
        DivisorException1() {
            super("输入错误");
        }
    }


    class Calculate {
        String str;
        Calculate(String str) {
            this.str=str;
        }
        //判断优先级
        int prior(char x) {
            if(x=='*'||x=='/') return 4;
            else if(x=='s'||x=='c'||x=='t'||x=='m'||x=='a'||x=='S'||x=='R'||x=='%')return 5;
            else if(x=='+'||x=='-') return 3;
            else if(x=='(') return 2;
            else if(x=='#') return 1;
            else return 0;
        }
        void transform() {
            String s="";
            char c,x;
            //建立栈对象
            Stack stack=new Stack();
            //添加一个#到栈中
            stack.push('#');
            this.str+="#";
            char n= str.charAt(0) ;
            char n2 = str.charAt(1);
            if(n=='0'){
                try{
                    if(n2!='.'&&n2!='+'&&n2!='-'&&n2!='/'&&n2!='*')
                        throw new DivisorException1();
                }catch (DivisorException1 e) {
                    new WarningGUI(e.getMessage());
                    new Calulator().t.setText("");
                }
            }
            for(int i=0;i<this.str.length();i++) {
                //取出字符串中的字符
                x=str.charAt(i);
                //判断是否是数字
                if(x>='0'&&x<='9'||x=='.') {
                    s+=x;
                    //该字符下一个不为数字时
                    if((str.charAt(i+1)<'0'||str.charAt(i+1)>'9')&&str.charAt(i+1)!='.')
                        s+=" ";
                }
                else switch(x) {
                    case '(':stack.push(x);break;
                    case ')':c=stack.pop();
                        while(c!='(') {
                            s+=c;
                            c=stack.pop();
                        }
                        break;
                    default :c=stack.getTop();
                        while(prior(c)>=prior(x)) {
                            s+=c;
                            c=stack.pop();
                            if(stack.length==0) break;
                            c=stack.getTop();
                        }
                        if(x!='#') stack.push(x);
                }
            }
            this.str=s.substring(0, s.length()-1);
        }
        String value() {
            float x1,x2;
            StackPro stackPro=new StackPro();
            String s="";
            for(int i=0;i<this.str.length();i++) {
                char c=str.charAt(i);
                switch (c) {
                    case 's' :x1=stackPro.pop(); stackPro.push((float)(Math.sin(x1)));break;
                    case 'c' :x1=stackPro.pop(); stackPro.push((float)(Math.cos(x1)));break;
                    case 't' :x1=stackPro.pop(); stackPro.push((float)(Math.tan(x1)));break;
                    case 'a' :x1=stackPro.pop(); stackPro.push((float)(Math.atan(x1)));break;
                    case 'S' :x1=stackPro.pop(); stackPro.push((float)(Math.sqrt(x1)));break;
                    case '%':x2=stackPro.pop();x1=stackPro.pop();stackPro.push(x1%x2);break;
                    case 'R' :x1=stackPro.pop();x2=1/x1; stackPro.push(x2);break;
                    case 'm':x1=stackPro.pop();x2=-x1;stackPro.push(x2);break;
                    case '+':x2=stackPro.pop();x1=stackPro.pop();stackPro.push(x1+x2);break;
                    case '-':x2=stackPro.pop();x1=stackPro.pop();stackPro.push(x1-x2);break;
                    case '*':x2=stackPro.pop();x1=stackPro.pop();stackPro.push(x1*x2);break;
                    case '/':x2=stackPro.pop();x1=stackPro.pop();
                        try {
                            if(x2==0)
                                throw new DivisorException();
                            stackPro.push(x1/x2);break;
                        }
                        catch (DivisorException e) {
                            new WarningGUI(e.getMessage());
                            return"error";
                        }
                    default:
                        while(str.charAt(i)!=' ') {
                            s+=str.charAt(i);
                            i++;
                        }
                        stackPro.push(Float.parseFloat(s));
                        s="";
                }
            }
            float y=stackPro.pop();
            BigDecimal b2 = new BigDecimal(y);
            y = b2.setScale(6, BigDecimal.ROUND_HALF_UP).floatValue();
            if(y<0){
                y=-y;
                return "m"+Float.toString(y);
            }
            if((int)y==y){
                return Integer.toString((int)y);}
            else{
                return Float.toString(y);}
        }
    }


    public static void main(String[] args) {
        Calulator c = new Calulator();
    }


}
