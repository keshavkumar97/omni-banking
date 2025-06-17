//package org.omni.bank.auth.model;
//
//import com.bank.common.RoleEnum;
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
//
//import java.time.LocalDateTime;
//
//@Entity
//public class Role {
//    @Id
//    @Column(name = "role_id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Getter
//    private long roleId;
//
//    @Column(name = "role_name", nullable = false, length = 5, unique = true)
//    @Getter
//    @Setter
//    @Enumerated(EnumType.STRING)
//    private RoleEnum roleName;
//
//    @Column(name = "description", nullable = false, length = 256)
//    @Getter
//    @Setter
//    private String description;
//
//    @CreationTimestamp
//    @Column(updatable = false, nullable = false)
//    @Getter
//    @Setter
//    private LocalDateTime createdAt;
//
//    @UpdateTimestamp
//    @Column(nullable = true)
//    @Getter
//    @Setter
//    private LocalDateTime updatedAt;
//
//    public Role() {    //NoArgsConstructor
//    }
//
//    public Role(RoleEnum roleName, String description, String roleCode) {
//        //AllArgsConstructor
//        this.roleName = roleName;
//        this.description = description;
//        this.roleCode = roleCode;
//    }
//}
