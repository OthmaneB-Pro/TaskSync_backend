package com.taskSync.TaskSync_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "jwt")
public class Jwt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String value;
    private boolean disable;
    private boolean expire;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE })
    @JoinColumn(name = "user_id")
    private User user;

    public Jwt() {
    }

    Jwt(int id, String value, boolean disable, boolean expire, User user) {
        this.id = id;
        this.value = value;
        this.disable = disable;
        this.expire = expire;
        this.user = user;
    }

    public String getValue() {
        return value;
    }

    public int getId() {
        return id;
    }

    public boolean isDisable() {
        return disable;
    }

    public boolean isExpire() {
        return expire;
    }

    public User getUser() {
        return user;
    }

    public static JwtBuilder builder() {
        return new JwtBuilder();
    }

    public static class JwtBuilder {
        private int id;
        private String value;
        private boolean disable;
        private boolean expire;
        private User user;

        public JwtBuilder() {
        }

        public JwtBuilder id(int id) {
            this.id = id;
            return this;
        }

        public JwtBuilder value(String value) {
            this.value = value;
            return this;
        }

        public JwtBuilder disable(boolean disable) {
            this.disable = disable;
            return this;
        }

        public JwtBuilder expire(boolean expire) {
            this.expire = expire;
            return this;
        }

        public JwtBuilder user(User user) {
            this.user = user;
            return this;
        }

        public Jwt build() {
            return new Jwt(id, value, disable, expire, user);
        }
    }
}