package ps.codetree.Simulation;

import java.util.*;
import java.io.*;
public class destroyTheTurret {
    public static class Node{
        int y;
        int x;
        public Node(int y,int x){
            this.y=y;
            this.x=x;
        }
    }
    public static int[][] arr;
    public static int n;
    public static int m;
    public static Deque<Node> damaged=new ArrayDeque<>();
    public static List<Node> fixed=new ArrayList<>();
    public static boolean dfsDone;
    public static int[][] recent;
    public static int[] dy={0,1,0,-1,1,-1,1,-1};
    public static int[] dx={1,0,-1,0,1,1,-1,-1};
    public static int[][] visited;
    public static int[][] prev;
    public static int[][] war;
    public static int index;

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st=new StringTokenizer(br.readLine());

        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        int k=Integer.parseInt(st.nextToken());
        arr=new int[n][m];
        recent=new int[n][m];
        visited=new int[n][m];
        prev = new int[n][m];
        war = new int[n][m];
        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                int temp=Integer.parseInt(st.nextToken());
                arr[i][j]=temp;
            }
        }

        for(int i=0;i<k;i++){

            // System.out.println(" ");
            // for(int [] q:arr){
            //     for(int qq:q){
            //         System.out.print(" "+qq);
            //     }
            //     System.out.println(" ");
            // }

            int onlyOne=0;
            for(int a=0;a<n;a++){
                for(int b=0;b<m;b++){
                    if(arr[a][b]>0){
                        onlyOne++;
                    }
                }
            }
            if(onlyOne==1){
                break;
            }

            //약한포탑
            Node weakest=weak();
            // System.out.println(weakest.y+" "+weakest.x);
            //강한포탑
            Node strongest=strong();
            arr[weakest.y][weakest.x]+=(n+m);
            // System.out.println(strongest.y+" "+strongest.x);
            //레이저공격 -> 1,-1
            int canLazer=lazer(weakest,strongest);
            //-1이면 폭탄공격
            if(canLazer==-1){
                boom(strongest);
            }
            int attack=arr[weakest.y][weakest.x];
            for(Node n:fixed){
                if(n.y==weakest.y && n.x==weakest.x){
                    continue;
                }
                if(n.y==strongest.y && n.x==strongest.x){
                    arr[n.y][n.x]-=attack;
                    if(arr[n.y][n.x]<0){
                        arr[n.y][n.x]=0;
                    }
                }
                else{
                    arr[n.y][n.x]-=(attack/2);
                    if(arr[n.y][n.x]<0){
                        arr[n.y][n.x]=0;
                    }
                }
            }

            // System.out.println(" ");
            // for(int [] q:arr){
            //     for(int qq:q){
            //         System.out.print(" "+qq);
            //     }
            //     System.out.println(" ");
            // }


            //포탑 정비
            for(Node n: fixed){
                war[n.y][n.x]=1;
            }
            war[weakest.y][weakest.x]=1;

            for(int a=0;a<n;a++){
                for(int b=0;b<m;b++){
                    if(arr[a][b]>0 && war[a][b]==0){
                        arr[a][b]++;
                    }
                }
            }
            for(int a=0;a<n;a++){
                Arrays.fill(war[a],0);
            }

            //index 바꾸기
            index++;
            recent[weakest.y][weakest.x]=index;
            fixed.clear();
            damaged.clear();
        }
        int answer=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                answer=Math.max(answer,arr[i][j]);
            }
        }

        // System.out.println(" ");
        //     for(int [] q:arr){
        //         for(int qq:q){
        //             System.out.print(" "+qq);
        //         }
        //         System.out.println(" ");
        //     }

        System.out.println(answer);

    }
    public static Node weak(){
        int min=5001;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(arr[i][j]!=0){
                    min=Math.min(min,arr[i][j]);
                }
            }
        }
        List<Node> weakList=new ArrayList<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(arr[i][j]==min){
                    weakList.add(new Node(i,j));
                }
            }
        }
        if(weakList.size()==1){
            return weakList.get(0);
        }
        int recentAttack=0;
        for(Node n:weakList){
            recentAttack=Math.max(recentAttack,recent[n.y][n.x]);
        }
        List<Node> weakList2=new ArrayList<>();
        for(Node n:weakList){
            if(recent[n.y][n.x]==recentAttack){
                weakList2.add(n);
            }
        }
        weakList.clear();
        if(weakList2.size()==1){
            return weakList2.get(0);
        }
        weakList2.sort((o1,o2)->{
            if((o1.y+o1.x)==(o2.y+o2.x)){
                return o2.x-o1.x;
            }
            else{
                return (o2.y+o2.x)-(o1.y+o1.x);
            }
        });
        return weakList2.get(0);
    }
    public static Node strong(){
        int max=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(arr[i][j]!=0){
                    max=Math.max(max,arr[i][j]);
                }
            }
        }
        List<Node> strongList=new ArrayList<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(arr[i][j]==max){
                    strongList.add(new Node(i,j));
                }
            }
        }
        if(strongList.size()==1){
            return strongList.get(0);
        }
        int recentAttack=1001;
        for(Node n:strongList){
            recentAttack=Math.min(recentAttack,recent[n.y][n.x]);
        }
        List<Node> strongList2=new ArrayList<>();
        for(Node n:strongList){
            if(recent[n.y][n.x]==recentAttack){
                strongList2.add(n);
            }
        }
        strongList.clear();
        strongList2.sort((o1,o2)->{
            if((o1.y+o1.x)==(o2.y+o2.x)){
                return o1.x-o2.x;
            }
            else{
                return (o1.y+o1.x)-(o2.y+o2.x);
            }
        });
        return strongList2.get(0);
    }
    public static int lazer(Node start,Node end){
        for(int i=0;i<n;i++){
            Arrays.fill(visited[i],0);
        }
        for(int i=0;i<n;i++){
            Arrays.fill(prev[i],0);
        }
        Deque<Node> deq=new ArrayDeque<>();
        deq.addLast(start);
        visited[start.y][start.x]=1;

        while(!deq.isEmpty()){
            Node now=deq.pollFirst();
            for(int i=1;i<5;i++){
                int ny=(now.y+dy[i-1]+n)%n;
                int nx=(now.x+dx[i-1]+m)%m;
                if(arr[ny][nx]!=0 && visited[ny][nx]==0){
                    visited[ny][nx]=1;
                    prev[ny][nx]=i;
                    deq.addLast(new Node(ny,nx));
                }
            }
        }

        if(prev[end.y][end.x]==0){
            return -1;
        }
        int ny=end.y;
        int nx=end.x;
        while(true){
            if(prev[ny][nx]==0){
                break;
            }
            fixed.add(new Node(ny,nx));
            int i=prev[ny][nx];
            ny=(ny-dy[i-1]+n)%n;
            nx=(nx-dx[i-1]+m)%m;
        }
        return 1;

    }
    // public static void dfs(int sy,int sx,int ey,int ex,int depth,int minLen){
    //     // for(Node n:damaged){
    //     //     System.out.print(" node: "+n.y+" "+n.x);
    //     // }

    //     // System.out.println(" depth:"+depth);
    //     // System.out.println(" ");
    //     if(dfsDone || depth>minLen){
    //         return ;
    //     }
    //     if(sy==ey && sx==ex && minLen==depth){
    //         dfsDone=true;
    //         // System.out.print("found");
    //         for(Node n : damaged){
    //             fixed.add(n);
    //         }
    //         return ;
    //     }
    //     for(int i=0;i<4;i++){
    //         int ny=(sy+dy[i]+n)%n;
    //         int nx=(sx+dx[i]+m)%m;
    //         if(visited[ny][nx]==0 && arr[ny][nx]!=0 && dfsDone==false){
    //             visited[ny][nx]=1;
    //             damaged.addLast(new Node(ny,nx));
    //             dfs(ny,nx,ey,ex,depth+1,minLen);
    //             visited[ny][nx]=0;
    //             damaged.pollLast();
    //         }
    //     }
    // }
    public static void boom(Node end){
        fixed.add(end);
        for(int i=0;i<8;i++){
            int ny=(end.y+dy[i]+n)%n;
            int nx=(end.x+dx[i]+m)%m;
            fixed.add(new Node(ny,nx));
        }
    }
}