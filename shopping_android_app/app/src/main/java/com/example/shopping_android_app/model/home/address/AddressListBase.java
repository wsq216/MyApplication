package com.example.shopping_android_app.model.home.address;

import java.util.List;

public class AddressListBase {

    /**
     * errno : 0
     * errmsg :
     * data : [{"id":4,"name":"北京","user_id":7,"country_id":0,"province_id":1,"city_id":1,"district_id":1,"address":"xxx","mobile":"13000000000","is_default":0,"province_name":"中国","city_name":"中国","district_name":"中国","full_region":"中国中国中国"},{"id":6,"name":"张文茜","user_id":7,"country_id":0,"province_id":5,"city_id":50,"district_id":629,"address":"御熙苑","mobile":"18514555529","is_default":1,"province_name":"山西省","city_name":"太原市","district_name":"小店区","full_region":"山西省太原市小店区"},{"id":7,"name":"张文茜","user_id":7,"country_id":0,"province_id":5,"city_id":50,"district_id":629,"address":"御熙苑","mobile":"18514555529","is_default":1,"province_name":"山西省","city_name":"太原市","district_name":"小店区","full_region":"山西省太原市小店区"},{"id":9,"name":"张文茜","user_id":7,"country_id":0,"province_id":5,"city_id":50,"district_id":629,"address":"御熙苑","mobile":"18514555529","is_default":1,"province_name":"山西省","city_name":"太原市","district_name":"小店区","full_region":"山西省太原市小店区"},{"id":10,"name":"张文茜","user_id":7,"country_id":0,"province_id":5,"city_id":50,"district_id":629,"address":"御熙苑","mobile":"18514555529","is_default":1,"province_name":"山西省","city_name":"太原市","district_name":"小店区","full_region":"山西省太原市小店区"},{"id":11,"name":"csk","user_id":7,"country_id":0,"province_id":2,"city_id":37,"district_id":407,"address":"123144325","mobile":"15311246590","is_default":1,"province_name":"北京","city_name":"北京市","district_name":"朝阳区","full_region":"北京北京市朝阳区"},{"id":12,"name":"csk","user_id":7,"country_id":0,"province_id":2,"city_id":37,"district_id":407,"address":"123144325","mobile":"15111111","is_default":1,"province_name":"北京","city_name":"北京市","district_name":"朝阳区","full_region":"北京北京市朝阳区"},{"id":13,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":14,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":15,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":16,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":25,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":26,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":27,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":28,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":29,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":30,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":31,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":32,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":34,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":35,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":36,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":37,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":38,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":39,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":40,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":41,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":42,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":43,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":44,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":45,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":46,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":47,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":48,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":49,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":50,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":51,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":52,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":53,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":54,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":55,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":56,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":57,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":58,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":59,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":60,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":61,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":62,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":63,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":64,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":65,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":66,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":67,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":68,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":69,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":70,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":71,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":72,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":73,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":74,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":75,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":76,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":77,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":78,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":79,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":80,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":81,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":82,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":83,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":84,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":85,"name":"","user_id":7,"country_id":0,"province_id":0,"city_id":0,"district_id":0,"address":"","mobile":"","is_default":0,"full_region":null},{"id":86,"name":"AUK","user_id":7,"country_id":0,"province_id":2,"city_id":37,"district_id":403,"address":"","mobile":"110","is_default":0,"province_name":"北京","city_name":"北京市","district_name":"东城区","full_region":"北京北京市东城区"},{"id":87,"name":"AUK","user_id":7,"country_id":0,"province_id":2,"city_id":37,"district_id":403,"address":"","mobile":"110","is_default":1,"province_name":"北京","city_name":"北京市","district_name":"东城区","full_region":"北京北京市东城区"}]
     */

    private int errno;
    private String errmsg;
    private List<DataBean> data;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 4
         * name : 北京
         * user_id : 7
         * country_id : 0
         * province_id : 1
         * city_id : 1
         * district_id : 1
         * address : xxx
         * mobile : 13000000000
         * is_default : 0
         * province_name : 中国
         * city_name : 中国
         * district_name : 中国
         * full_region : 中国中国中国
         */

        private int id;
        private String name;
        private int user_id;
        private int country_id;
        private int province_id;
        private int city_id;
        private int district_id;
        private String address;
        private String mobile;
        private int is_default;
        private String province_name;
        private String city_name;
        private String district_name;
        private String full_region;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getCountry_id() {
            return country_id;
        }

        public void setCountry_id(int country_id) {
            this.country_id = country_id;
        }

        public int getProvince_id() {
            return province_id;
        }

        public void setProvince_id(int province_id) {
            this.province_id = province_id;
        }

        public int getCity_id() {
            return city_id;
        }

        public void setCity_id(int city_id) {
            this.city_id = city_id;
        }

        public int getDistrict_id() {
            return district_id;
        }

        public void setDistrict_id(int district_id) {
            this.district_id = district_id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getIs_default() {
            return is_default;
        }

        public void setIs_default(int is_default) {
            this.is_default = is_default;
        }

        public String getProvince_name() {
            return province_name;
        }

        public void setProvince_name(String province_name) {
            this.province_name = province_name;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getDistrict_name() {
            return district_name;
        }

        public void setDistrict_name(String district_name) {
            this.district_name = district_name;
        }

        public String getFull_region() {
            return full_region;
        }

        public void setFull_region(String full_region) {
            this.full_region = full_region;
        }
    }
}
