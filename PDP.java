/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdp;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class PDP {

    public static int u, v, h, src =0 ;
    public static int node_number=100;
    public static int error_node=499;
    public static int forward_node_count=0;
    public static int REACHED_node_count;
    public static int[] visited;
    public static int[] array_c= new int[10000];
    public static int[] connected_u = new int[10000];
    public static int fo = 0;
    public static int[] visited_queue_node = new int[10000];
    public static int[] visited_p_node = new int[10000];
    public static int[] REACHED_node = new int[10000];
    public static int visited_queue_node_count = 0;
    public static int[] nu_size_bidirection;
    public static int[] n2u_size_bidirection;
    public static int visited_count;

    /**
     * ******* Queue Forward list Track **********
     */
    public static Queue<Integer> q_forward = new LinkedList<>();

    /**
     * ****** Queue forward list parent Track ****
     */
    public static Queue<Integer> q_parent_forward = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        for(int dd=1;dd<=10;dd++)
        {
            forward_node_count=0;
            REACHED_node_count=0;
           // System.out.println("FILE"+dd);
        try {

            visited = new int[100000];
            visited_count = 0;
            File f = new File("scen-100-225-625-625-"+dd+".out");
            Vset[] vset;
            int i_count = 0;
            vset = new Vset[12000];
            nu_size_bidirection=new int[12000];
            n2u_size_bidirection=new int[12000];
          
            FSET[] fset;
            fset = new FSET[12000];

            BufferedReader b = new BufferedReader(new FileReader(f));

            String readLine = "";

            //System.out.println("Reading file using Buffered Reader");

            //System.out.println("Length= "+tokens.length);
            
            for (int i = 0; i < n2u_size_bidirection.length; i++) {
                
                nu_size_bidirection[i]=0;
                n2u_size_bidirection[i]=0;
            }
            
            
            
            int i = -1;

            while (i <node_number) {

                i++;

                while ((readLine = b.readLine()) != null) {
                    String[] tokens = readLine.split(" ");

                    if (tokens.length == 3) {

                        u = Integer.valueOf(tokens[tokens.length - 3]);
                        v = Integer.valueOf(tokens[tokens.length - 2]);
                        h = Integer.valueOf(tokens[tokens.length - 1]);

                        int visited_flag = 0;

                        for (int l2 = 0; l2 < visited_count; l2++) {

                            if (visited[l2] == u) {
                                visited_flag = 1;
                                break;
                            }
                        }

                        if (visited_flag == 0) {
                            
                            if(nu_size_bidirection[u]==0)
                            {
                                vset[u] = new Vset();  // create each actual Person
                            }
                          
                            
                            
                            visited[visited_count] = u;
                            visited_count++;
                            int k = 0, j = 0;
                            //System.out.println("u= " + u);
                            // System.out.println("v= " + v);
                            //System.out.println("h= " + h);

                            vset[u].nu[nu_size_bidirection[u]] = u;
                           
                            nu_size_bidirection[u]=nu_size_bidirection[u]+1;
                            
                            k++;

                            File f2 = new File("scen-100-225-625-625-"+dd+".out");

                            BufferedReader b2 = new BufferedReader(new FileReader(f2));

                            String readLine2 = "";

                            //System.out.println("Reading file of UU "+u);
                            while ((readLine2 = b2.readLine()) != null) {
                                String[] tokens2 = readLine2.split(" ");
                                int u2, v2, h2;
                                if (tokens2.length == 3) {
                                    u2 = Integer.valueOf(tokens2[tokens2.length - 3]);
                                    v2 = Integer.valueOf(tokens2[tokens2.length - 2]);
                                    h2 = Integer.valueOf(tokens2[tokens2.length - 1]);
                                    
                                    
                                    if(n2u_size_bidirection[v2]==0 && nu_size_bidirection[v2]==0)
                                    {
                                        vset[v2] = new Vset();  // create each actual Person
                                    }
                                    

                                    if (u2 == u) {
                                        

                                        vset[u].u = u2;
                                       // System.out.println("vset icount ="  +i_count+" : "+  vset[i_count].u);

                                        // ******************** Neighbour **************//
                                        if (h2 == 1) {
                                          
                                            vset[u].nu[nu_size_bidirection[u]] = v2;
                                            nu_size_bidirection[u]=nu_size_bidirection[u]+1;
                                            
                                           //System.out.println("neighbour of v:" + v2);
                                            
                                           vset[v2].nu[nu_size_bidirection[v2]] = u;
                                             
                                          nu_size_bidirection[v2]=nu_size_bidirection[v2]+1;
                                            
                                            k++;
                                           
                                            

                                        }

                                        // ****************** Neighbour(Neighbour) *********//
                                        if (h2 == 2) {
                                             
                                            vset[u].n2u[n2u_size_bidirection[u]] = v2;
                                            n2u_size_bidirection[u]=n2u_size_bidirection[u]+1;
                                           //  System.out.println("neighbour of neighbour of v:"+vset[i].n2u[j]); 
                                            j++;
                                            vset[v2].n2u[n2u_size_bidirection[v2]] = u;
                                            //System.out.println("neighbour of neighbour of v:"+u); 
                                           n2u_size_bidirection[v2]=n2u_size_bidirection[v2]+1;
                                            
                                          
                                            
                                            
                                        }
                                    }

                                }

                            }

                            vset[u].nu_size = nu_size_bidirection[u];
                            vset[u].n2u_size = n2u_size_bidirection[u];
                            vset[node_number-1].n2u_size = n2u_size_bidirection[node_number-1];
                            vset[node_number-1].nu_size = nu_size_bidirection[node_number-1];
                           
                         // System.out.println(u+": nusize: "+vset[u].nu_size);  
           
                         // System.out.println(u+": n2u size "+vset[u].n2u_size);
                            i_count++;

                        }

                    }

                }

            }

            //********* For Loop Ends *********//
            
            
          /*  for (int l2 = 0; l2 < visited_count; l2++) {

                     int uuu=visited[l2];
                 
                     System.out.println("Node: "+uuu);  
                     
                     System.out.print("N(U): "); 
                 
                    for (int j = 0; j < vset[uuu].nu_size; j++) {

                        System.out.print(vset[uuu].nu[j]+",");  


                   }
                    
                    System.out.println(); 
                    
                    System.out.print("N(N(U)): "); 
                  System.out.print(uuu+","); 
                    for (int j = 0; j < vset[uuu].n2u_size; j++) {

                        System.out.print(vset[uuu].n2u[j]+",");  


                   }
                 
                   System.out.println(); 
    
            }*/
            
            
            
            /**
             * ********** Src Queue Push *******
             */
            int max_src = -9999999;
            
            q_forward.add(src);
            q_parent_forward.add(max_src);
            visited_count++;
            visited[visited_count-1]=node_number-1;
            
            /**
             * ********* Queue not empty ********
             */
            int u3, v3, parent;
           
            
            for (int ii = 0; ii < visited_queue_node.length; ii++) {
                visited_queue_node[ii] = 0;
            }
             for (int ii = 0; ii < REACHED_node.length; ii++) {
               REACHED_node[ii] = 0;
            }
           for (int hi = 0; hi < array_c.length; hi++) {
                array_c[hi] = 0;
            }
            while (!q_forward.isEmpty()) {

                // To remove the head of queue.
                u3 = q_forward.remove();

              //  System.out.println("u3"+u3);
                
                parent = q_parent_forward.remove();
               //  System.out.println("u"+parent);
               // System.out.println("v"+u3);

               if (visited_queue_node[u3] != 1) {
                   
                   visited_queue_node[u3] = 1;
                   
                forward_node_count++;
                
                   
                

                if (parent == -9999999) {
                   // System.out.println("Parent Calculate:");
                   //System.out.println("u:"+src);

                    vset[src].b_size = vset[src].nu_size;
                    vset[src].u_size = vset[src].n2u_size;
                    //System.out.println("Source:" + src);
                    
                    // ************** Source B set *****************// 
                    for (int j = 0; j < vset[src].nu_size; j++) {
                         if (REACHED_node[vset[src].nu[j]] != 1) {
                                     REACHED_node[vset[src].nu[j]] = 1;
                                 }
                         
                        
                      //  if(vset[src].nu[j]!=error_node)
                     //   {
                           vset[src].b[j] = vset[src].nu[j];
                     //   }

                    }

                    for (int j = 0; j < vset[src].n2u_size; j++) {
                        

                        vset[src].uset[j] = vset[src].n2u[j];

                    }
                  /*  for (int j = 0; j < vset[src].b_size; j++) {
                        
                        System.out.println("THE B SET:"+vset[src].b[j]);
                    }*/

                    // **************** Src Forwad list find out *******************//
                    /*System.out.print("B SET:");
                    
                    for (int j = 0; j < vset[src].b_size; j++) {

                         System.out.print(vset[src].b[j]+",");
                     }
                    System.out.println();
                    System.out.print("U SET:");
                       for (int j = 0; j < vset[src].u_size; j++) {
                           
                        System.out.print(vset[src].uset[j]+",");
                       
                     }
                      System.out.println();*/
                    for (int j = 0; j < vset[src].b_size; j++) {

                        int g = vset[src].b[j];
                     //   System.out.println("G="+g);

                        for (int Vset_count1 = 0; Vset_count1 < visited_count; Vset_count1++) {

                            int x = 0;

                            int u31, v31;

                            u31 = visited[Vset_count1];
                          //  System.out.println("u31"+u31);

                            if (u31 == g) {
                             //System.out.println("u31"+u31);
                                for (int k = 0; k < vset[g].nu_size; k++) {

                                    // ************** Comapre Src Uset with B set Neghbour *********// 
                                    for (int p = 0; p < vset[src].u_size; p++) {

                                        if (vset[src].uset[p] == vset[g].nu[k]) {

                                           // System.out.println("UUUUU: " + vset[g].nu[k]);
                                            connected_u[x] = vset[g].nu[k];

                                            x++;

                                        }

                                    }

                                }
                                // System.out.println("count"+g+"="+count_forward_set);

                                /*for (int y = 0; y < x; y++) {

                                        System.out.println("connected to "+g+"="+connected_u[y]);

                                    }*/
                                fset[g] = new FSET();

                                fset[g].u = src;
                                fset[g].v = g;
                                fset[g].size = x;

                                for (int y = 0; y < fset[g].size; y++) {
                                    fset[g].connected[y] = connected_u[y];
                                }

                            }

                        }

                    }

                    int[] array_index_sort = new int[vset[src].b_size];
                    int[] array_size_sort = new int[vset[src].b_size];
                    

                    for (int fset_count = 0; fset_count < vset[src].b_size; fset_count++) {
                      
                       // if(vset[src].b[fset_count]!=error_node)
                       // { 
                            
                            
                        int b_set_value = vset[src].b[fset_count];
                      
               // System.out.println("u: " + b_set_value);
                        array_size_sort[fset_count] = fset[b_set_value].size;
                        array_index_sort[fset_count] = b_set_value;

                        //System.out.println("u: " + b_set_value);
                         //System.out.println("Size: " +fset[b_set_value].size);
                        
                       /*  for (int jj = 0; jj < fset[b_set_value].size; jj++) {

                                System.out.println("conneted nodes:" + fset[b_set_value].connected[jj]);

                            }*///}
                       
                    }

                    //********************* Array Sort***************//
                    int n = array_size_sort.length;

                    for (int l = 0; l < n - 1; l++) {

                        for (int j = 0; j < n - l - 1; j++) {
                            if (array_size_sort[j] < array_size_sort[j + 1]) {
                                // swap temp and arr[i]
                                int temp = array_size_sort[j];
                                int temp2 = array_index_sort[j];

                                array_size_sort[j] = array_size_sort[j + 1];
                                array_index_sort[j] = array_index_sort[j + 1];

                                array_size_sort[j + 1] = temp;
                                array_index_sort[j + 1] = temp2;
                            }
                        }
                    }
                    
                    
                     for (int hi = 0; hi < array_c.length; hi++) {
                            array_c[hi] = 0;
                        }

                    for (int j = 0; j < array_index_sort.length - 1; j++) {

                        int b_set_value1 = array_index_sort[j];

                        //System.out.println("value 1 : " + b_set_value1);
                        //System.out.println("Value 2 sizeYY: " + fset[b_set_value1].size);
                        
                        for (int k = j + 1; k < array_index_sort.length; k++) {
                          
                            int b_set_value2 = array_index_sort[k];
                            
                           // System.out.println("Value 2: " + b_set_value2);
                            //System.out.println("Value 2 sizeYYY: " + fset[b_set_value2].size);
                           
                            if (fset[b_set_value2].size != 0) {

                               // System.out.println("Value 2: " + b_set_value2);
                                int c = 0;

                                for (int l = 0; l < fset[b_set_value1].size; l++) {

                                   // System.out.println("l fset_connected: " + fset[b_set_value1].connected[l]);
                                   
                                    for (int m = 0; m < fset[b_set_value2].size; m++) {

                                        // System.out.println("m fset connected: " + fset[b_set_value2].connected[m]);
                                         
                                        if (fset[b_set_value1].connected[l] == fset[b_set_value2].connected[m]) {

                                           // System.out.println("Match found for "+fset[b_set_value2].connected[m]+": " + fset[b_set_value1].connected[l]);
                                            c++;
                                            array_c[b_set_value2]=array_c[b_set_value2]+1;
                                        }

                                    }

                                }

                                if (array_c[b_set_value2] == fset[b_set_value2].size) {
                                    // System.out.println("counttttttttttttttttttt: " + c);
                                    //  System.out.println("Match2: " + b_set_value2);
                                    fset[b_set_value2].size = 0;
                                }

                            }

                        }

                    }
                 // System.out.print("F SET:");
                    for (int jj = 0; jj < array_index_sort.length; jj++) {
                    
                      
                      
                        if (fset[array_index_sort[jj]].size != 0) {
                            //System.out.print(array_index_sort[jj]+",");
                            q_forward.add(array_index_sort[jj]);
                            q_parent_forward.add(src);
                        }

                    }
                    //System.out.println();

                    /* System.out.println("queue: "+q_forward.size());
                            
                           while(!q_forward.isEmpty()){
                                   int node = q_forward.poll();
                                   int pa=q_parent_forward.poll();
                                   System.out.println("removed element: "+pa+"-- "+ node);
                            
                           }*/
                } else {

                    /**
                     * ********** Not Source ***********
                     */
                   // System.out.println("Not Source Calculate:");

                  // System.out.println("removed element: " + parent + "-- " + u3);
                     //System.out.println("u:"+ parent);
                     // System.out.println("v:"+ u3);
                    vset[src].u_size = vset[src].n2u_size;

                    // ************** Source B set *****************// 
                    int b_ct = 0;

                    for (int j = 0; j < vset[u3].nu_size; j++) {

                        int node = vset[u3].nu[j];
                        if (REACHED_node[vset[u3].nu[j]] != 1) {
                                     REACHED_node[vset[u3].nu[j]] = 1;
                                 }
                         

                        int flag_b = 0;

                        for (int k = 0; k < vset[parent].nu_size; k++) {

                            int node2 = vset[parent].nu[k];

                            // System.out.println("parent set: "+node2);
                            if (node == node2) {

                                // System.out.println("Matched--- set: "+node2); 
                                flag_b = 1;
                                break;
                            }

                        }

                        if (flag_b == 0) {
                           // if (vset[u3].b[b_ct] != error_node) {
                                vset[u3].b[b_ct] = node;

                                b_ct++;
                           // }
                        }

                    }

                    /**
                     * ******** B set size ***********
                     */
                    vset[u3].b_size = b_ct;

                    
                      
                      // ************** Source P set *****************// 
                    int p_ct = 0;

                    for (int j = 0; j < vset[u3].nu_size; j++) {

                        int node = vset[u3].nu[j];
                        if (REACHED_node[vset[u3].nu[j]] != 1) {
                                     REACHED_node[vset[u3].nu[j]] = 1;
                                 }
                         

                        int flag_p = 0;

                        for (int k = 0; k < vset[parent].nu_size; k++) {

                            int node2 = vset[parent].nu[k];

                            // System.out.println("parent set: "+node2);
                            if (node == node2) {

                                // System.out.println("Matched--- set: "+node2); 
                                flag_p = 1;
                                break;
                            }

                        }

                        if (flag_p == 1 && node!=u3 && node !=parent) {
                           // if (vset[u3].b[b_ct] != error_node) {
                                vset[u3].pset[p_ct] = node;

                                p_ct++;
                           // }
                        }

                    }

                    /**
                     * ******** B set size ***********
                     */
                    for (int ip = 0; ip < visited_p_node.length; ip++) {
                        visited_p_node[ip] = 0;
                     }
                    vset[u3].p_size = p_ct;
                    int p_ct2 = 0;
                    for (int j = 0; j < vset[u3].p_size; j++) {
                        
                        int pnode=vset[u3].pset[j];
                         for (int k = 0; k < vset[pnode].nu_size; k++) {

                            int pnode2 = vset[pnode].nu[k];
                            if(visited_p_node[pnode2] != 1 && pnode2!=pnode)
                            {
                                vset[u3].pset2[p_ct2]=pnode2;
                                 visited_p_node[pnode2] = 1;
                                 p_ct2++;
                            }
                         }

                        
                     }
                     vset[u3].p2_size = p_ct2;
                     
                             
                    /**
                     * ************ Uset Calculate **********
                     */
                    int u_ct2 = 0;

                    for (int j = 0; j < vset[u3].n2u_size; j++) {

                        int node = vset[u3].n2u[j];
                        
                         
                        int flag_b = 0;

                        for (int k = 0; k < vset[parent].nu_size; k++) {

                            int node2 = vset[parent].nu[k];
                            

                            // System.out.println("parent set: "+node2);
                            if (node == node2) {

                                // System.out.println("Matched--- set: "+node2); 
                                flag_b = 1;
                                break;
                            }

                        }

                        if (flag_b == 0) {
                            vset[u3].upset[u_ct2] = node;

                            u_ct2++;
                        }

                    }

                    /**
                     * ************ Upset Size **********
                     */
                    vset[u3].up_size = u_ct2;
                    
                      int u_ct = 0;

                    for (int j = 0; j < vset[u3].up_size; j++) {

                        int node = vset[u3].upset[j];
                         //System.out.println("upset set: "+node);
                         
                        int flag_u = 0;

                        for (int k = 0; k < vset[u3].p2_size; k++) {

                            int node2 = vset[u3].pset2[k];
                            

                             //System.out.println("p set: "+node2);
                            if (node == node2) {

                               //  System.out.println("Matched--- set: "+node2); 
                                flag_u = 1;
                                break;
                            }

                        }

                        if (flag_u == 0) {
                            vset[u3].uset[u_ct] = node;

                            u_ct++;
                        }

                    }

                    /**
                     * ************ Upset Size **********
                     */
                    vset[u3].u_size = u_ct;
                    
                   /* for (int j = 0; j < vset[u3].b_size; j++) {

                         System.out.println("B SET:"+vset[u3].b[j]);
                     }
                    for (int j = 0; j < vset[u3].u_size; j++) {

                         System.out.println("U SET:"+vset[u3].uset[j]);
                     }*/
                   /*System.out.print("B SET:");
                    for (int j = 0; j < vset[u3].b_size; j++) {

                         System.out.print(vset[u3].b[j]+",");
                     }
                     System.out.println();
                     System.out.print("P SET:");
                    for (int j = 0; j < vset[u3].p2_size; j++) {

                         System.out.print(vset[u3].pset2[j]+",");
                     }
                     System.out.println();
                    System.out.print("U SET:");
                       for (int j = 0; j < vset[u3].u_size; j++) {
                        System.out.print(vset[u3].uset[j]+",");
                       
                     }
                      System.out.println();*/
                    // **************** Src Forwad list find out *******************//
                    for (int j = 0; j < vset[u3].b_size; j++) {

                        int g = vset[u3].b[j];
                       // System.out.println("G="+g);

                        //System.out.print("********** The B set ************: ");
                        //System.out.println(g);

                        int x = 0;

                        int u31, v31;

                        //System.out.println("g"+g);
                       
                       // if(g!=error_node)
                        
                       // {   
                            for (int k = 0; k < vset[g].nu_size; k++) {

                            // System.out.println("N(" + g + ")=" + vset[g].nu[k]);
                            // ************** Comapre Src Uset with B set Neghbour *********// 
                            for (int p = 0; p < vset[u3].u_size; p++) {

                                // System.out.println("UUUUU: " + vset[u3].uset[p]);
                                if (vset[u3].uset[p] == vset[g].nu[k]) {

                                    // System.out.println("Match Found----::: " + vset[g].nu[k]);
                                    connected_u[x] = vset[g].nu[k];

                                    x++;

                                }

                            }

                        }
                       // }
                        // System.out.println("count"+g+"="+count_forward_set);

                       /*  for (int y = 0; y < x; y++) {
                                    
                                    System.out.println("connected to "+g+"="+connected_u[y]);
                                    
                            }*/
                        fset[g] = new FSET();
                        fset[g].size = x;

                        for (int y = 0; y < fset[g].size; y++) {
                            fset[g].connected[y] = connected_u[y];
                        }

                              /*  for (int y = 0; y < fset[g].size; y++) {
                             
                                 System.out.println("connected to "+g+"="+fset[g].connected[y]);
                                     
                                }*/
                    }

                    int[] array_index_sort = new int[vset[u3].b_size];
                    int[] array_size_sort = new int[vset[u3].b_size];

                    //System.out.println("----------Before-------");
                    for (int fset_count = 0; fset_count < vset[u3].b_size; fset_count++) {

                        int b_set_value = vset[u3].b[fset_count];

                        array_size_sort[fset_count] = fset[b_set_value].size;
                        array_index_sort[fset_count] = b_set_value;

                        //System.out.println("u: " + b_set_value);
                        // System.out.println("Size: " +fset[b_set_value].size);
                        /*for (int jj = 0; jj < fset[b_set_value].size; jj++) {

                                System.out.println("conneted nodes:" + fset[b_set_value].connected[jj]);

                            }*/
                    }

                    //********************* Array Sort***************//
                    int n = array_size_sort.length;

                    for (int l = 0; l < n - 1; l++) {
                        for (int j = 0; j < n - l - 1; j++) {
                            if (array_size_sort[j] < array_size_sort[j + 1]) {
                                // swap temp and arr[i]
                                int temp = array_size_sort[j];
                                int temp2 = array_index_sort[j];

                                array_size_sort[j] = array_size_sort[j + 1];
                                array_index_sort[j] = array_index_sort[j + 1];

                                array_size_sort[j + 1] = temp;
                                array_index_sort[j + 1] = temp2;
                            }
                        }
                    }

                  /*  for (int jj = 0; jj < vset[u3].b_size; jj++) {

                                System.out.println("Size nodes:" + array_size_sort[jj]);
                                System.out.println("Index nodes:" + array_index_sort[jj]);
                                
                                
                            }*/
                    
                    for (int hi = 0; hi < array_c.length; hi++) {
                            array_c[hi] = 0;
                        }
                    
                    
                    // For loop Starts
                    for (int j = 0; j < array_index_sort.length - 1; j++) {

                        int b_set_value1 = array_index_sort[j];

                       // System.out.println("value 1 : " + b_set_value1);
                       // System.out.println("Value 1 SIZE: " + fset[b_set_value1].size);
                        
                        
                        for (int k = j + 1; k < array_index_sort.length; k++) {

                            int b_set_value2 = array_index_sort[k];

                            if (fset[b_set_value2].size != 0) {

                               // System.out.println("Value 2: " + b_set_value2);
                                
                                // System.out.println("Value 2 SIZE: " + fset[b_set_value2].size);
                                 
                                 
                                
                                
                                int c = 0;

                                for (int l = 0; l < fset[b_set_value1].size; l++) {

                                    //System.out.println("l fset_connected: " + fset[b_set_value1].connected[l]);
                                    
                                    for (int m = 0; m < fset[b_set_value2].size; m++) {

                                       // System.out.println("m fset connected: " + fset[b_set_value2].connected[m]);
                                        
                                        if (fset[b_set_value1].connected[l] == fset[b_set_value2].connected[m]) {

                                            //  System.out.println("Match found for "+fset[b_set_value2].connected[m]+": " + fset[b_set_value1].connected[l]);
                                            c++;
                                            
                                             array_c[b_set_value2]=array_c[b_set_value2]+1;
                                        }

                                    }

                                }

                                if (array_c[b_set_value2] == fset[b_set_value2].size) {
                                    // System.out.println("counttttttttttttttttttt: " + c);
                                   // System.out.println("Match2: " + b_set_value2);
                                    fset[b_set_value2].size = 0;
                                }

                            }

                        }

                    }

                    // For loop ends
                    //System.out.print("F SET: ");
                    for (int jj = 0; jj < array_index_sort.length; jj++) {
                      
                        if (fset[array_index_sort[jj]].size != 0) {
                            //System.out.println(array_index_sort[jj]+",");
                            q_forward.add(array_index_sort[jj]);
                            q_parent_forward.add(u3);
                        }

                    }
                    //System.out.println();

                }
                }
              
            }
          System.out.println(forward_node_count);
             /*for (int ii = 0; ii < REACHED_node.length; ii++) {
               if(REACHED_node[ii] != 0)
               {
                   REACHED_node_count++;
               }
            }
             System.out.println(REACHED_node_count);*/

        } catch (IOException e) {
            e.printStackTrace();
        }
        }

    }

    
}
