<template>
  <div v-if="isAuth(['ROOT', 'DRIVER:SELECT'])">
    <el-form :inline="true" :model="searchForm" :rules="dataRule" ref="searchForm">
      <el-form-item prop="name">
        <el-input v-model="searchForm.name" placeholder="司机姓名" size="medium" class="input" clearable="clearable" />
      </el-form-item>
      <el-form-item><el-button size="medium" type="primary" @click="searchHandle()">查询</el-button></el-form-item>
      <el-form-item>
        <el-button size="medium" type="primary" @click="showUploadContractDialog">上传合同</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      cell-style="padding: 2px 0"
      style="width: 100%"
      size="medium"
      @expand-change="expand"
      :row-key="getRowKeys"
      :expand-row-keys="expands"
    >
      <el-table-column prop="content" header-align="center" align="center" type="expand">
        <template #default="scope">
          <div class="content-container">
            <div class="left">
              <table class="content-table">
                <tbody>
                  <tr>
                    <th>开车驾龄</th>
                    <th>生日</th>
                    <th>邮箱</th>
                    <th>收信地址</th>
                    <th>身份证住址</th>
                  </tr>
                  <tr>
                    <td>{{ scope.row.year }}年</td>
                    <td>{{ scope.row.birthday }}</td>
                    <td>{{ scope.row.email }}</td>
                    <td>{{ scope.row.mailAddress }}</td>
                    <td>{{ scope.row.idcardAddress }}</td>
                  </tr>
                </tbody>
              </table>
              <ul class="credentials-list">
                <li class="item">
                  <el-image
                    style="width: 350px; height: 220px"
                    :src="scope.row.idcardFront"
                    :preview-src-list="scope.row.idcardFrontList"
                    fit="cover"
                  />
                  <p>【 身份证正面 】</p>
                </li>
                <li class="item">
                  <el-image
                    style="width: 350px; height: 220px"
                    :src="scope.row.idcardBack"
                    :preview-src-list="scope.row.idcardBackList"
                    fit="cover"
                  />
                  <p>【 身份证背面 】</p>
                </li>
                <li class="item">
                  <el-image
                    style="width: 350px; height: 220px"
                    :src="scope.row.idcardHolding"
                    :preview-src-list="scope.row.idcardHoldingList"
                    fit="cover"
                  />
                  <p>【 手持身份证 】</p>
                </li>
                <li class="item">
                  <el-image
                    style="width: 350px; height: 220px"
                    :src="scope.row.drcardFront"
                    :preview-src-list="scope.row.drcardFrontList"
                    fit="cover"
                  />
                  <p>【 驾驶证正面 】</p>
                </li>
                <li class="item">
                  <el-image
                    style="width: 350px; height: 220px"
                    :src="scope.row.drcardBack"
                    :preview-src-list="scope.row.drcardBackList"
                    fit="cover"
                  />
                  <p>【 驾驶证背面 】</p>
                </li>
                <li class="item">
                  <el-image
                    style="width: 350px; height: 220px"
                    :src="scope.row.drcardHolding"
                    :preview-src-list="scope.row.drcardHoldingList"
                    fit="cover"
                  />
                  <p>【 手持驾驶证 】</p>
                </li>
              </ul>
            </div>
            <div class="right" v-if="scope.row.realAuthStatus == '已认证'">
              <div class="summary-panel">
                <h3 class="title">【 代驾数据摘要 】</h3>
                <ul class="monthly-list">
                  <li class="item">
                    <p class="section">月订单金额</p>
                    <p class="value">
                      <span>￥</span>
                      {{ scope.row.total }}
                    </p>
                  </li>
                  <li class="item">
                    <p class="section">月订单分账</p>
                    <p class="value">
                      <span>￥</span>
                      {{ scope.row.income }}
                    </p>
                  </li>
                  <li class="item">
                    <p class="section">代扣个税</p>
                    <p class="value">
                      <span>￥</span>
                      {{ scope.row.tax }}
                    </p>
                  </li>
                </ul>
                <ul class="statistics-list">
                  <li class="item">
                    <p class="section">总代驾单数</p>
                    <p class="value">{{ scope.row.count }}</p>
                  </li>
                  <li class="item">
                    <p class="section">好评总数</p>
                    <p class="value">{{ scope.row.ct1 }}</p>
                  </li>
                  <li class="item">
                    <p class="section">差评总数</p>
                    <p class="value">{{ scope.row.ct2 }}</p>
                  </li>
                  <li class="item">
                    <p class="section">差评总数</p>
                    <p class="value">{{ scope.row.ct3 }}</p>
                  </li>
                </ul>
              </div>
              <div id="chart"></div>
            </div>
          </div>
        </template>
      </el-table-column>
      <el-table-column prop="name" header-align="center" align="center" min-width="80" label="姓名" />
      <el-table-column
        prop="sex"
        header-align="center"
        align="center"
        min-width="60"
        label="性别"
        :formatter="sexFormatter"
      />
      <el-table-column prop="phone" header-align="center" align="center" min-width="110" label="电话" />
      <el-table-column prop="idNumber" header-align="center" align="center" min-width="180" label="身份证" />
      <el-table-column prop="contactName" header-align="center" align="center" min-width="110" label="紧急联络人" />
      <el-table-column prop="contactPhone" header-align="center" align="center" min-width="110" label="联络人电话" />
      <el-table-column prop="realAuthStatus" header-align="center" align="center" min-width="130" label="实名认证">
        <template #default="scope">
          <el-tag v-if="scope.row.realAuthStatus === 0" type="info">未实名</el-tag>
          <el-tag v-if="scope.row.realAuthStatus === 1" type="primary">审核中</el-tag>
          <el-tag v-if="scope.row.realAuthStatus === 2" type="success">已认证</el-tag>
          <el-tag v-if="scope.row.realAuthStatus === 3" type="danger">已驳回</el-tag>
        </template>
      </el-table-column>

      <el-table-column prop="realAuthStatus" header-align="center" align="center" min-width="100" label="司机状态">
        <template #default="scope">
          <el-tag v-if="scope.row.realAuthStatus === 0" type="success">启用</el-tag>
          <el-tag v-if="scope.row.realAuthStatus === 1" type="danger">禁用</el-tag>
        </template>
      </el-table-column>
      <el-table-column header-align="center" align="center" width="250" label="操作">
        <template #default="scope">
          <el-button
            type="text"
            size="medium"
            v-if="isAuth(['ROOT', 'USER:UPDATE'])"
            @click="showApproveModel(scope.row.id)"
            :disabled="scope.row.realAuthStatus != 0"
          >
            审批认证
          </el-button>
          <el-button
            type="text"
            size="medium"
            v-if="isAuth(['ROOT', 'USER:UPDATE'])"
            @click="showRepealModel(scope.row.id)"
            :disabled="scope.row.realAuthStatus != 2"
          >
            撤销认证
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="sizeChangeHandle"
      @current-change="currentChangeHandle"
      :current-page="page"
      :page-sizes="[10, 20, 50]"
      :page-size="rows"
      :total="totalCount"
      layout="total, sizes, prev, pager, next, jumper"
    ></el-pagination>

    <!-- 页面对话框区域 -->
    <div class="dialogs">
      <el-dialog v-model="approveModelVisible" title="提示信息" width="400px" center>
        <div class="notice">你确定已经核准该代驾司机的实名认证？</div>
        <div>
          <el-input type="text" v-model="auditRemark" placeholder="填写审核备注" />
        </div>
        <template #footer>
          <span class="dialog-footer">
            <el-button type="danger" @click="approveHandle(false)">拒绝</el-button>
            <el-button type="primary" @click="approveHandle(true)">批准</el-button>
          </span>
        </template>
      </el-dialog>
      <el-dialog v-model="repealModelVisible" title="提示信息" width="400px" center>
        <div class="notice">你确定取消该代驾司机的实名认证？</div>
        <div>
          <el-input type="text" v-model="cancelAuditRemark" placeholder="填写审核备注" />
        </div>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="repealModelVisible = false">取消</el-button>
            <el-button type="primary" @click="repealHandle">确定</el-button>
          </span>
        </template>
      </el-dialog>

      <!-- 合同上传框 -->
      <el-dialog
        title="合同上传"
        v-model="isShowUploadContractDialog"
        width="480px"
        center
        align-center
        :close-on-click-modal="false"
      >
        <el-form :model="driverContract">
          <el-form-item label="选择司机" label-width="80">
            <el-select style="width: 320px" v-model="driverContract.driverId" filterable placeholder="请选择">
              <el-option v-for="d in drivers" :key="d.value" :label="d.nickName" :value="d.id"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="合同编号" label-width="80">
            <el-input style="width: 320px" v-model="driverContract.agreementSn" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="合同选择" label-width="80">
            <el-upload
              :headers="{ satoken: satoken }"
              ref="imgUpload"
              class="upload-demo"
              action="http://127.0.0.1:10010/drive/common/minio"
              :on-remove="handleRemove"
              :on-success="handleSuccess"
              :on-exceed="exceed"
              :file-list="fileList"
              :limit="1"
              accept=".jpg,.jpeg,.png,.gif"
              list-type="picture"
            >
              <el-button size="small" type="primary">点击上传</el-button>
              <!-- <div slot="tip" class="el-upload__tip">只能上传指定类型的图片</div> -->
            </el-upload>
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="isShowUploadContractDialog = false">取 消</el-button>
            <el-button type="primary" @click="confirmUploadDriverArgreement">确 定</el-button>
          </span>
        </template>
      </el-dialog>
    </div>
    <update ref="update" @refreshDataList="loadDataList"></update>
  </div>
</template>
<script>
import $ from "jquery";
import request from "../utils/request";
import Update from "./driver-update.vue";
import { ElMessage } from "element-plus";
export default {
  components: {
    Update,
  },
  data() {
    return {
      cancelAuditRemark: "",
      auditRemark: "",
      searchForm: {
        name: null,
      },
      chart: "",
      dataList: [],
      page: 1,
      rows: 10,
      totalCount: 0,
      dataListLoading: false,
      dataRule: {
        name: [
          {
            required: false,
            pattern: "^[\u4e00-\u9fa5]{1,10}$",
            message: "姓名格式错误",
          },
        ],
        phone: [
          {
            required: false,
            pattern: "^1\\d{10}$",
            message: "电话格式错误",
          },
        ],
        idNumber: [
          {
            required: false,
            pattern: "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$",
            message: "身份证格式错误",
          },
        ],
      },
      expands: [],
      content: {
        year: null,
        birthday: null,
        email: null,
        mailAddress: null,
        idcardAddress: null,
        idcardFront: null,
        idcardFrontList: [],
        idcardBack: null,
        idcardBackList: [],
        idcardHolding: null,
        idcardHoldingList: [],
        drcardFront: null,
        drcardFrontList: [],
        drcardBack: null,
        drcardBackList: [],
        drcardHolding: null,
        drcardHoldingList: [],
        total: null,
        income: null,
        tax: null,
        count: null,
        ct1: null,
        ct2: null,
        ct3: null,
      },
      approveModelVisible: false,
      repealModelVisible: false,
      driverMaterialId: null,
      getRowKeys(row) {
        return row.id;
      },
      //==============
      // 是否展示司机合同上传对话框
      isShowUploadContractDialog: false,
      // 合同相关信息
      driverContract: {
        driverId: "",
        driverName: "",
        agreementSn: "",
        agreementPathSigned: "",
      },
      // 司机们
      drivers: [],
      fileList: [], //文件上传
      imgBaseUrl: "http://47.109.72.16:9090/aikuiba-fccar/", // 图片前置url
      satoken: "",
    };
  },
  methods: {
    /**
     * 加载司机列表
     */
    loadDrivers() {
      this.post("/driver/manager/driver/list", (resp) => {
        console.log("===>", resp);
      });
    },
    confirmUploadDriverArgreement() {
      console.log("===>", this.driverContract);
    },
    /**
     * 删除Minio图片文件
     * @param {string} url 完整可访问的远程图片地址
     * @param {function} _callback 回调函数
     */
    delMinioPicture(url, _callback) {
      if (url) {
        let fileName = url.substring(url.lastIndexOf("/") + 1);
        if (fileName) {
          // 发送删除请求到8080服务器
          this.$http
            .delete(`/minio/${fileName}`)
            .then(({ data }) => {
              // 如果传了回调函数,这里就回去执行回调函数且将服务器返回的data传给回调函数
              if (_callback && typeof _callback == "function") _callback(data);
            })
            .catch((resp) => {
              this.$message.error("服务器异常!");
            });
        }
      }
    },
    // 上传成功回调
    handleSuccess(response, file, files) {
      if (response.success) {
        let imgPath = this.imgBaseUrl + response.data;
        this.driverContract.agreementPathSigned = imgPath;
        this.fileList = [];
        this.fileList.push({ url: imgPath });
      } else {
        this.$message.error("文件上传失败!");
      }
    },
    // 超出文件个数限制回调
    exceed(file, files) {
      this.$message.info("超出文件上传个数[1]限制!");
    },
    // 移除图片回调
    handleRemove(file, files) {
      // url: "http://minio.java.itsource.cn/java0820/1700561661791.png"
      this.delMinioPicture(file.url, (data) => {
        if (data.success) {
          //清空fileList
          this.$refs.imgUpload.clearFiles();
          this.$message.success("删除成功!");
          // 删除图片后应该将当前数据中的articlePic也清空
          this.saveForm.articlePic = "";
          return;
        }
        this.$message.error("删除失败!");
      });
    },
    // 展示合同上传对话框
    showUploadContractDialog() {
      this.isShowUploadContractDialog = true;
    },
    updateDriverStatus(driverMaterialId) {
      this.$confirm("确定要修改司机状态？").then(() => {
        this.sendPost("/driver/driver/changestatus/" + driverMaterialId, (res) => {
          let { success, message, result } = res;
          if (success) {
            this.$message({
              message: "修改成功",
              type: "success",
              duration: 1200,
            });
            this.loadDataList();
          } else {
            this.$message({
              message: message,
              type: "error",
              duration: 1200,
            });
          }
        });
      });
    },
    //加载列表数据
    loadDataList() {
      let _this = this;
      let pageParam = {
        page: _this.page,
        rows: _this.rows,
      };
      _this.post("/driver/manager/driver/pagelist", pageParam, (resp) => {
        console.log(resp);
        let { success, code, data, message } = resp;
        if (success) {
          console.log("data", data);
          _this.dataList = data.rows;
          _this.totalCount = data.total;
        }
      });
    },
  },
  mounted() {
    // 设置satoken
    this.satoken = localStorage.getItem("satoken");
    // this.loadDataList();
    this.loadDrivers();
  },
};
</script>
<style lang="less" scoped="scoped">
@import url("driver.less");
</style>
