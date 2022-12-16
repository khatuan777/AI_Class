#include<stdio.h>
#include<conio.h>
#include<iostream.h>
#define max 32767
struct element{
	int dinh;
	element *next;
};
typedef element *List;
List L;
int g[20][20],h[20],f[20],d[20],truoc[20],visit[20];
int n,m,s,ff;
void Create(List &L)
{
	L=NULL;
};
void insertFirst(List &L,int u)
{
	List p;
	p= new element();
	(*p).dinh=u; (*p).next=L;
	L=p;
};
void insertElement(List &L,int u)
{
	List p,after,befor;
	p= new element();
	(*p).dinh=u;
	after=L; befor=L;
	while(after!=NULL && f[(*after).dinh]<f[u]) 
	{
		befor=after;
		after=(*after).next;
	}
	(*p).next=after;
	if(after==L) L=p;
	else (*befor).next=p;
};
void deleteFirst(List &L)
{
	List p;
	p=new element();
	if(L!=NULL)
	{
		p=L;
		L=(*p).next;
		delete p;
	}
}
void input()
{
	FILE *fi;
	int i,j,u,v,w,z;
	fi=fopen("Astar.INP","r");
	fscanf(fi,"%d%d%d%d",&n,&m,&s,&ff);
	for(i=1;i<=n;i++)
	  for(j=1;j<=n;j++)
	   g[i][j]=max;
	for(i=1;i<=m;i++)
	{
		fscanf(fi,"%d%d%d%d",&u,&v,&w,&z);
		g[u][v]=w;
		h[v]=z;	
	}
	fclose(fi);
}
void init()
{
	int i;
	for(i=1;i<=n;i++)
	{
	  d[i]= max;
	  truoc[i]=0;
	  visit[i]=0;
	}
	 d[s]=0;
};
void Astar()
{
	List p;
	Create(L);
	insertFirst(L,s);
	int i,u,v;
	do{
		p=L;
		u=(*p).dinh;
		deleteFirst(L);
		visit[u]=1;
		if(u==ff) return;
		for(v=1;v<=n;v++)
		    if(visit[v]==0 && d[v]> d[u]+g[u][v])
		    {
    		     d[v]=d[u]+g[u][v];
    		     truoc[v]=u;
    		     f[v]=d[v]+h[v];
    		     insertElement(L,v);
    		}
	}while(L!=NULL);
};
void print()
{
	int i;
	if(d[ff]==max) { printf("khong co duong di"); return;}
	printf("chi phi it nhat la: %d\n",d[ff]);
    printf("duong di ngan nhat tu dinh %d toi dinh %d la: ",s,ff);
    while(ff!=s)
    {
      printf("%d <-- ",ff)	;
      ff=truoc[ff];
    }
    printf("%d\n",s);
};
main()
{
	input();
	init();
	Astar();
	print();
	getch();
}
