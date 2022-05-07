package xyz.innky.graduationproject.web.params;

class ChangePasswordParam {
        private String oldPassword;
        private String newPassword;

        public String getOldPassword() {
            return oldPassword;
        }

        public void setOldPassword(String oldPassword) {
            this.oldPassword = oldPassword;
        }

        public String getNewPassword() {
            return newPassword;
        }

        public void setNewPassword(String newPassword) {
            this.newPassword = newPassword;
        }

        @Override
        public String toString() {
            return "Param{" +
                    "oldPassword='" + oldPassword + '\'' +
                    ", newPassword='" + newPassword + '\'' +
                    '}';
        }
    }