package com.nsu.common;

public enum Role {
	//	0：不需要登录就能访问的
	//  1：学生
	//  2：教师
	//	3：学校级
	//	4：区县级
	//	5：市级
	//	6：省级
	//	7：管理员
	
	
	Admin("admin",new int[]{7}),
	/* province 省*/
	Education("education",new int[]{4,5,6}),
	/* school 学校 */
	School("school",new int[]{3}),
	/* teacher 教师 */
	Teacher("teacher",new int[2]),
	/* student2 学生 */
	Student("student2",new int[1]);
	
	
	
	private int[] types;
	private String roleName;
	private Role(String roleName, int[] types){
		this.roleName = roleName;
		this.types = types;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public int[] getTypes() {
		return types;
	}
	public void setTypes(int[] types) {
		this.types = types;
	}
	
	
	
	public static boolean containType(Role role, int type){
		return search(role.getTypes(),type);
	}
	
	
	public static boolean search(int[] nums, int num) {  
        int low = 0;  
        int high = nums.length - 1;  
  
        while (low <= high) {  
            int mid = (low + high) / 2;  
            if (num > nums[mid]) {  
                low = mid + 1;  
            } else if (num < nums[mid]) {  
                high = mid - 1;  
            } else {  
                return true;  
            }  
        }  
  
        return false;  
    }  
	
}
