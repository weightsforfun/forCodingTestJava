package ps.codetree.Simulation;

import java.util.*;
import java.io.*;

public class mazeRunner {
    public static int [][] maze;
    public static int [][] distance;
    public static int [][] shortDistance;
    public static int answer;
    public static int n;
    public static class Node{
        int y;
        int x;
        int dis;
        public Node(int y, int x, int dis){
            this.y=y;
            this.x=x;
            this.dis=dis;
        }
    }
    public static List<Node> man=new ArrayList<>();
    public static int[] end=new int[2];
    public static int[] dy={-1,1,0,0,-1,1,-1,1};
    public static int[] dx={0,0,-1,1,-1,-1,1,1};
    public static void main(String[] args)throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());
        int k=Integer.parseInt(st.nextToken());
        maze=new int[n][n];
        distance = new int[n][n];
        shortDistance= new int[n][n];

        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                int temp=Integer.parseInt(st.nextToken());
                maze[i][j]=temp;
            }
        }

        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            int ty=Integer.parseInt(st.nextToken())-1;
            int tx=Integer.parseInt(st.nextToken())-1;
            man.add(new Node(ty,tx,0));
        }

        st=new StringTokenizer(br.readLine());
        end[0]=Integer.parseInt(st.nextToken())-1;
        end[1]=Integer.parseInt(st.nextToken())-1;


        // for(int []d :shortDistance){
        //     for(int dd:d){
        //         System.out.print(dd+" ");
        //     }
        //     System.out.println(" ");
        // }


        for(int i=0;i<k;i++){
            System.out.println((i+1)+"초 경과 "+"현재 남은 사람 수"+man.size());
            System.out.println("움직인 수" +answer);
            System.out.println("출구 y " +(end[0]+1)+" x "+(end[1]+1));
            renewDistance(end[0],end[1]);
            // if(i==2){
            //     for(int []d :distance){
            //         for(int dd:d){
            //             System.out.print(dd+" ");
            //         }
            //         System.out.println(" ");
            //     }
            // }
            move();
            if(man.size()==0){
                break;
            }
            rotateWall(end[0],end[1]);
            //이동
            //벽회전
            //정사각형 찾기
            //실제 회전
            //거리 갱신
        }
        System.out.println(answer);
        System.out.println((end[0]+1)+" "+(end[1]+1));
    }
    public static void move(){
        // System.out.println("move start");
        int len=man.size();
        Deque<Node> deq=new ArrayDeque<>();
        for(Node m: man){
            deq.addLast(m);
        }
        // System.out.println("end y "+end[0]+" end x "+end[1]);
        // for(Node m: man){
        //     System.out.println("m.y "+m.y+" m.x "+m.x+" ");
        // }
        for(int i=0;i<len;i++){
            Node now=deq.pollFirst();
            boolean flag=true;
            for(int j=0;j<4;j++){
                int ny=dy[j]+now.y;
                int nx=dx[j]+now.x;
                if(check(ny,nx) && distance[now.y][now.x]>distance[ny][nx] && maze[ny][nx]==0){
                    flag=false;
                    answer++;
                    if(ny==end[0] && nx==end[1]){
                        break;
                    }
                    deq.addLast(new Node(ny,nx,0));
                    break;
                }
            }
            if(flag){
                deq.addLast(now);
            }
        }
        man.clear();
        while(!deq.isEmpty()){
            man.add(deq.pollFirst());
        }
        // for(Node m: man){
        //     System.out.println("m.y "+m.y+" m.x "+m.x+" ");

        // }
//         System.out.println(answer);
//         System.out.println(end[0]+" "+end[1]);
        // System.out.println("move done");

    }
    public static boolean check(int y, int x){
        return (y>=0 && y<n && x>=0 && x<n);
    }
    public static void renewShortDistance(int ey,int ex){
        Deque<Node> deq=new ArrayDeque<>();
        deq.addLast(new Node(ey,ex,0));
        int [][] visited=new int[n][n];
        visited[ey][ex]=1;
        shortDistance[ey][ex]=0;
        while(!deq.isEmpty()){
            Node now=deq.pollFirst();
            for(int i=0;i<8;i++){
                int ny=now.y+dy[i];
                int nx=now.x+dx[i];
                if(check(ny,nx) && visited[ny][nx]==0){
                    visited[ny][nx]=1;
                    shortDistance[ny][nx]=now.dis+1;
                    deq.addLast(new Node(ny,nx,now.dis+1));
                }
            }
        }
    }
    public static void renewDistance(int ey,int ex){
        // System.out.println("renew done");
        Deque<Node> deq=new ArrayDeque<>();
        deq.addLast(new Node(ey,ex,0));
        int [][] visited=new int[n][n];
        visited[ey][ex]=1;
        distance[ey][ex]=0;
        while(!deq.isEmpty()){
            Node now=deq.pollFirst();
            for(int i=0;i<4;i++){
                int ny=now.y+dy[i];
                int nx=now.x+dx[i];
                if(check(ny,nx) && visited[ny][nx]==0){
                    visited[ny][nx]=1;
                    distance[ny][nx]=now.dis+1;
                    deq.addLast(new Node(ny,nx,now.dis+1));
                }
            }
        }
    }
    public static List<Node> findOld(int ey,int ex){
        // System.out.println("find start");
        renewShortDistance(ey,ex);
        List<List<Node>> rec=new ArrayList<>();

        int min=1000;
        for(Node p:man){
            min=Math.min(min,shortDistance[p.y][p.x]);
        }
        for(Node p:man){
            if(shortDistance[p.y][p.x]==min){
                List<Node> tList=new ArrayList<>();
                tList.add(p);
                tList.add(new Node(ey,ex,0));
//                tList.sort((o1,o2)->{
//                    if(o1.y==o2.y){
//                        return o1.x-o2.x;
//                    }
//                    else{
//                        return o1.y-o2.y;
//                    }
//                });
                rec.add(tList);

            }
        }
        rec.sort((o1,o2)->{
            if(o1.get(0).y==o2.get(0).y){
                return o1.get(0).x-o2.get(0).x;
            }
            else{
                return o1.get(0).y-o2.get(0).y;
            }
        });

        List<Node> fixed=rec.get(0);
        Node a=fixed.get(0);
        Node b=fixed.get(1);
        // System.out.println(a.y);
        // System.out.println(a.x);
        // System.out.println(b.y);
        // System.out.println(b.x);

        int top=0;
        int down=min;
        int left=0;
        int right=min;
        while(true){
            if(a.x>=left && a.x<=right && b.x>=left && b.x<=right){
                break;
            }
            left++;
            right++;
        }
        while(true){
            if(a.y>=top && a.y<=down && b.y>=top && b.y<=down){
                break;
            }
            top++;
            down++;
        }
        System.out.println("a.y "+(a.y+1)+" a.x "+(a.x+1));


        List<Node> points=new ArrayList<>();
        points.add(new Node(top,left,0));
        points.add(new Node(down,right,0));
        System.out.println("y "+(top+1)+" x "+(left+1)+" size "+(min+1));
//        System.out.println("y "+(down+1)+" x "+(right+1));
        return points;
    }

    public static List<Node> find(int ey,int ex){
        renewShortDistance(ey,ex);
        List<List<Node>> rec=new ArrayList<>();

        int min=1000;
        for(Node p:man){
            min=Math.min(min,shortDistance[p.y][p.x]);
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(ey>=i && ey<=i+min && ex>=j && ex<=j+min){
                    for(Node m:man){
                        if(m.y>=i && m.y<=i+min && m.x>=j && m.x<=j+min){
                            List<Node> points=new ArrayList<>();
//                            System.out.println("좌상단 y "+(i+1)+" x "+(j+1)+" len "+(min+1));
                            points.add(new Node(i,j,0));
                            points.add(new Node(i+min,j+min,0));
                            return points;
                        }
                    }
                }
            }
        }
        List<Node> poo=new ArrayList<>();
        return poo;
    }
    public static void rotateWall(int ey,int ex){
        // System.out.println("rotate start");
        List<Node> points=find(ey,ex);
        int top=points.get(0).y;
        int left=points.get(0).x;
        int down=points.get(1).y;
        int right=points.get(1).x;

        for(Node m:man){
            maze[m.y][m.x]--;
        }
        maze[ey][ex]=100;

        int len=down-top+1;

        // for(int [] ma:maze){
        //     for(int dd:ma){
        //         System.out.print(dd+" ");
        //     }
        //     System.out.println(" ");
        // }


        for(int i=0;i<len/2;i++){
            for(int j=0;j<len;j++){
                int temp=maze[down-i][left+j];
                maze[down-i][left+j]=maze[top+i][left+j];
                maze[top+i][left+j]=temp;
            }
        }

        for(int i=0;i<len;i++){
            for(int j=i;j<len;j++){
                int temp=maze[top+i][left+j];
                maze[top+i][left+j]=maze[top+j][left+i];
                maze[top+j][left+i]=temp;
            }
        }

        for(int i=0;i<len;i++){
            for(int j=0;j<len;j++){
                int tempy=top+i;
                int tempx=left+j;
                if(maze[tempy][tempx]>=1 && maze[tempy][tempx]<=9){
                    maze[tempy][tempx]--;
                }
            }
        }

        // for(int [] ma:maze){
        //     for(int dd:ma){
        //         System.out.print(dd+" ");
        //     }
        //     System.out.println(" ");
        // }
        // System.out.println("=========================================================");
        man.clear();
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(maze[i][j]<0){
                    while(maze[i][j]<0){
                        man.add(new Node(i,j,0));
                        maze[i][j]++;
                    }
                }
                else if(maze[i][j]==100){
                    maze[i][j]=0;
                    end[0]=i;
                    end[1]=j;
                }
            }
        }
        // for(int [] ma:maze){
        //     for(int dd:ma){
        //         System.out.print(dd+" ");
        //     }
        //     System.out.println(" ");
        // }
        // System.out.println("=========================================================");
        // System.out.println("rotate done");



    }

}
