package ps.codetree.Simulation;
import java.util.*;
import java.io.*;
public class trainingField {
    public static class Man{
        int y;
        int x;
        int di;
        int base;
        int gun;
        int index;

        public Man(int y,int x,int di,int base,int gun,int index){
            this.y=y;
            this.x=x;
            this.di=di;
            this.base=base;
            this.index=index;
            this.gun=gun;
        }
    }
    public static int[] dy={-1,0,1,0};
    public static int[] dx={0,1,0,-1};

    public static int n;
    public static int m;
    public static int k;
    public static int[][][] arr;
    public static int[][] people;
    public static Man[] men;
    public static int[] answer;
    public static void main(String[] args)throws IOException {
        // 여기에 코드를 작성해주세요.
        init();
        for(int i=0;i<k;i++){
            game();
            // debug();

        }
        for(int a:answer){
            System.out.print(a+" ");
        }
        System.out.println(" ");



    }
    public static void game(){
        for(int i=1;i<=m;i++){
            move(men[i]);
            // debug();
        }

    }
    public static void debug(){
        // System.out.println("------------gun-----------------");
        //     for(int[] m:arr){
        //         for(int a:m){
        //             System.out.print(a+" ");
        //         }
        //         System.out.println(" ");
        //     }
        //     System.out.println("------------gun-----------------");
        System.out.println("------------man-----------------");
        for(int[] m:people){
            for(int a:m){
                System.out.print(a+" ");
            }
            System.out.println(" ");
        }
        System.out.println("------------man-----------------");
    }
    public static void move(Man man){

        int y=man.y;
        int x=man.x;
        int ny=y+dy[man.di];
        int nx=x+dx[man.di];
        if(!(ny>=0 && ny<n && nx>=0 && nx<n)){
            man.di=(man.di+2)%4;
            ny=ny+dy[man.di]*2;
            nx=nx+dx[man.di]*2;
        }
        people[y][x]=0;
        if(people[ny][nx]==0){

            people[ny][nx]=man.index;
            man.y=ny;
            man.x=nx;
            checkGun(man);
        }
        else{
            man.y=ny;
            man.x=nx;
            fight(man,men[(people[ny][nx])]);
        }


    }
    public static void checkGun(Man man){
        if(arr[man.y][man.x][0]>arr[man.y][man.x][1] && man.gun<arr[man.y][man.x][0]){
            int temp=man.gun;
            man.gun=arr[man.y][man.x][0];
            arr[man.y][man.x][0]=temp;
        }
        else if(arr[man.y][man.x][0]<arr[man.y][man.x][1] && man.gun<arr[man.y][man.x][1]){
            int temp=man.gun;
            man.gun=arr[man.y][man.x][1];
            arr[man.y][man.x][1]=temp;
        }

    }
    public static void fight(Man man1,Man man2){
        Man winner;
        Man loser;
        if(man1.base+man1.gun>man2.base+man2.gun){
            winner=man1;
            loser=man2;
        }
        else if(man1.base+man1.gun<man2.base+man2.gun){
            winner=man2;
            loser=man1;
        }
        else{
            if(man1.base>man2.base){
                winner=man1;
                loser=man2;
            }
            else{
                winner=man2;
                loser=man1;
            }
        }
        //winner 포인트
        answer[winner.index-1]+=(winner.gun+winner.base-loser.gun-loser.base);
        //루저 총 버림
        if(arr[loser.y][loser.x][0]<arr[loser.y][loser.x][1] && loser.gun>arr[loser.y][loser.x][0]){
            arr[loser.y][loser.x][0]=loser.gun;
        }
        else if(arr[loser.y][loser.x][0]>arr[loser.y][loser.x][1] && loser.gun>arr[loser.y][loser.x][1]){
            arr[loser.y][loser.x][1]=loser.gun;
        }
        loser.gun=0;
        //루저 이동
        for(int i=0;i<4;i++){
            int ny=loser.y+dy[(loser.di+i)%4];
            int nx=loser.x+dx[(loser.di+i)%4];
            if((ny>=0 && ny<n && nx>=0 && nx<n) && people[ny][nx]==0){
                people[ny][nx]=loser.index;
                loser.y=ny;
                loser.x=nx;
                loser.di=(loser.di+i)%4;
                checkGun(loser);
                break;
            }
            else{
                ny-=dy[(loser.di+i)%4];
                nx-=dx[(loser.di+i)%4];
            }
        }
        //winner 총변경
        people[winner.y][winner.x]=winner.index;
        checkGun(winner);


    }
    public static void init()throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        k=Integer.parseInt(st.nextToken());
        arr=new int[n][n][2];
        people=new int[n][n];
        men=new Man[m+1];
        answer=new int[m];

        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                arr[i][j][0]=Integer.parseInt(st.nextToken());
            }
        }
        for(int i=1;i<=m;i++){
            st=new StringTokenizer(br.readLine());
            int y=Integer.parseInt(st.nextToken())-1;
            int x=Integer.parseInt(st.nextToken())-1;
            int di=Integer.parseInt(st.nextToken());
            int base=Integer.parseInt(st.nextToken());
            men[i]=new Man(y,x,di,base,0,i);
            people[y][x]=i;
        }
    }

}
