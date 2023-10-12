package ps.codetree.Simulation;



import java.util.*;
import java.io.*;

public class treeKill {
    public static int n;
    public static int m;
    public static int k;
    public static int c;
    public static int[][] arr;
    public static int[][] poison;
    public static int year;

    public static int[] dy= {1,-1,0,0,1,1,-1,-1};
    public static int[] dx= {0,0,1,-1,1,-1,1,-1};
    public static int answer=0;
    public static class Node{
        int y;
        int x;
        int direction;
        int depth;
        public Node(int y, int x, int direction) {
            this.y=y;
            this.x=x;
            this.direction=direction;
        }
        public Node(int y, int x, int direction,int depth) {
            this.y=y;
            this.x=x;
            this.direction=direction;
            this.depth=depth;
        }
    }
    public static void main(String[] args)throws IOException {
        //init
        init();
        //
        year=0;
        while(year<m) {
            year++;
//			for(int i=0;i<n;i++) {
//				for(int j=0;j<n;j++) {
//					System.out.print(arr[i][j]+" ");
//				}
//				System.out.println(" ");
//			}
            //나무 성장
            grow();
            //나무 번식
            breed();
            //제초제 뿌리기
            kill();
        }
        System.out.println(answer);


    }
    public static void kill() {
        int max=0;
        Node maxNode=new Node(0,0,0,0);
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                if(arr[i][j]<=0) continue;
                int tempSum=arr[i][j];
                for(int p=4;p<8;p++) {
                    int ny=i;
                    int nx=j;
                    for(int u=0;u<k;u++) {
                        ny+=dy[p];
                        nx+=dx[p];
                        if(!isIn(ny,nx) || arr[ny][nx]<0) break;
                        tempSum+=arr[ny][nx];
                        if(arr[ny][nx]==0) break;
                    }

                }
                if(tempSum>max) {
                    max=tempSum;
                    maxNode=new Node(i,j,0,0);
                }
            }
        }


//		System.out.println("year "+year+ " y"+maxNode.y+" x"+maxNode.x+" sum "+max );

        if(max==0) return ;
        answer+=arr[maxNode.y][maxNode.x];
        arr[maxNode.y][maxNode.x]=0;
        poison[maxNode.y][maxNode.x]=year+c;
        for(int p=4;p<8;p++) {
            int ny=maxNode.y;
            int nx=maxNode.x;
            for(int u=0;u<k;u++) {
                ny+=dy[p];
                nx+=dx[p];
                if(!isIn(ny,nx) || arr[ny][nx]<0) break;
                poison[ny][nx]=year+c;
                answer+=arr[ny][nx];
                if(arr[ny][nx]==0) break;
                arr[ny][nx]=0;
            }

        }


    }
    public static void breed() {
        int[][] visited=new int[n][n];
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                if(arr[i][j]>0) {
                    visited[i][j]=1;
                }
                else if (arr[i][j]==-1) {
                    visited[i][j]=-1;
                }
            }
        }
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                if(arr[i][j]<=0) continue;
                if(visited[i][j]==0)continue;
                int count=0;
                for(int p=0;p<4;p++) {
                    int ny=i+dy[p];
                    int nx=j+dx[p];
                    if(!isIn(ny,nx) || visited[ny][nx]!=0 || poison[ny][nx]>=year) continue;
                    count++;
                }
                if(count==0) continue;
                int extra=arr[i][j]/count;
                for(int p=0;p<4;p++) {
                    int ny=i+dy[p];
                    int nx=j+dx[p];
                    if(!isIn(ny,nx) || visited[ny][nx]!=0 || poison[ny][nx]>=year) continue;
                    arr[ny][nx]+=extra;
                }

            }
        }
    }
    public static void grow() {
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                if(arr[i][j]<=0) continue;
                int count=0;
                for(int p=0;p<4;p++) {
                    int ny=i+dy[p];
                    int nx=j+dx[p];
                    if(!isIn(ny,nx) || arr[ny][nx]<=0) continue;
                    count++;
                }
                arr[i][j]+=count;
            }
        }
    }
    public static boolean isIn(int y, int x) {
        return y>=0 && y<n && x>=0 && x<n;
    }
    public static void init()throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        k=Integer.parseInt(st.nextToken());
        c=Integer.parseInt(st.nextToken());
        arr=new int[n][n];
        poison=new int[n][n];

        for(int i=0;i<n;i++) {
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++) {
                arr[i][j]=Integer.parseInt(st.nextToken());
            }
        }


    }
}


