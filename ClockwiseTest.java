package com.example.restservice;

import java.util.*;

public class ClockwiseTest {
    public static void main(String[] args) {
        int n; // no of meetings

        Scanner scanner = new Scanner(System.in);
        n = Integer.parseInt(scanner.nextLine());
        int[][] meetings = new int[n][];
        System.out.println("enter meeting members");
        for(int i=0; i<n; i++) {
            String attendances = scanner.next();  //provide comma separated integers
            String[] sArray = attendances.trim().split(",");
            meetings[i] = new int[sArray.length];
            int j=0;
            for (String s:sArray) {
                if(s.trim().length()>0) {
                    meetings[i][j++] = Integer.parseInt(s);
                }
            }
        }

        System.out.println(Arrays.deepToString(meetings));

        int maxAttendeeMeeting = 0;
        List<int[]> meetingsSelected = new ArrayList<>();
        for (int[] meeting : meetings) {
            if(maxAttendeeMeeting < meeting.length) {
                maxAttendeeMeeting = meeting.length;
            }
            if(meetingsSelected.isEmpty() ) {
                meetingsSelected.add(meeting);
            } else {
                int[] remove = new int[0];
                int[] add = new int[0];
                for (int[] a : meetingsSelected) {
                    Set<Integer> set1 = new HashSet<>();
                    Set<Integer> set2 = new HashSet<>();
                    for (int i : a) {
                        set1.add(i);
                    }
                    for (int i : meeting) {
                        set2.add(i);
                    }

                    set1.retainAll(set2);
                    if (set1.size() > 0) {
                        if (a.length < meeting.length) {
                            remove = a;
                        }
                        add = new int[0];
                        break;
                    } else {
                        add = meeting;
                    }
                }
                if(remove.length>0) {
                    meetingsSelected.remove(remove);
                    meetingsSelected.add(meeting);
                } else if(add.length>0) {
                    meetingsSelected.add(meeting);
                }
            }
        }

        System.out.println("Meetings selected");
        meetingsSelected.forEach(a ->{
            System.out.println(Arrays.toString(a));
            System.out.println("total members in meeting "+ a.length);
        });

        Optional<int[]> max = meetingsSelected.stream().max(Comparator.comparingInt(o -> o.length));
        System.out.println(Arrays.toString(max.get()));
        System.out.println("total members in meeting "+ max.get().length);
    }
}
