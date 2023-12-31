<template>
	<div v-if="isAuth(['ROOT', 'DEPT:SELECT'])">
		<el-form :inline="true" :model="dataForm" :rules="dataRule" ref="dataForm">
			<el-form-item prop="deptName"><el-input v-model="dataForm.deptName" placeholder="部门名称" size="medium" class="input" clearable="clearable" /></el-form-item>
			<el-form-item>
				<el-button size="medium" type="primary" @click="searchHandle()">查询</el-button>
				<el-button size="medium" type="primary" :disabled="!isAuth(['ROOT', 'DEPT:INSERT'])" @click="addHandle()">新增</el-button>
				<el-button size="medium" type="danger" :disabled="!isAuth(['ROOT', 'DEPT:DELETE'])" @click="deleteHandle()">批量删除</el-button>
			</el-form-item>
		</el-form>
		<el-table :data="dataList" border v-loading="dataListLoading" @selection-change="selectionChangeHandle" cell-style="padding: 4px 0" size="medium" style="width: 100%;">
			<el-table-column type="selection" :selectable="selectable" header-align="center" align="center" width="50" />
			<el-table-column type="index" header-align="center" align="center" width="100" label="序号">
				<template #default="scope">
					<span>{{ (page - 1) * rows + scope.$index + 1 }}</span>
				</template>
			</el-table-column>
			<el-table-column prop="deptName" header-align="center" align="center" label="部门名称" min-width="170" />
			<el-table-column prop="tel" header-align="center" align="center" label="联系电话" min-width="170" />
			<el-table-column prop="email" header-align="center" align="center" label="邮箱" min-width="270" />
			<el-table-column header-align="center" align="center" label="员工数量" min-width="140">
				<template #default="scope">
					<span>{{ scope.row.emps }}人</span>
				</template>
			</el-table-column>
			<el-table-column prop="desc" header-align="center" align="center" label="备注" min-width="400" />
			<el-table-column header-align="center" align="center" width="150" label="操作">
				<template #default="scope">
					<el-button type="text" size="medium" :disabled="!isAuth(['ROOT', 'DEPT:UPDATE'])" @click="updateHandle(scope.row.id)">修改</el-button>
					<el-button type="text" size="medium" :disabled="!isAuth(['ROOT', 'DEPT:DELETE']) || scope.row.emps > 0" @click="deleteHandle(scope.row.id)">删除</el-button>
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
		<add-or-update ref="addOrUpdate" @refreshDataList="loadDataList"></add-or-update>
	</div>
</template>

<script>
import AddOrUpdate from './dept-add-or-update.vue';
export default {
	components: {
		AddOrUpdate
	},
	data: function() {
		return {
			dataForm: {
				deptName: null
			},
			dataList: [],
			page: 1,
			rows: 10,
			totalCount: 0,
			dataListLoading: false,
			dataListSelections: [],
			dataRule: {
				deptName: [{ required: false, pattern: '^[a-zA-Z0-9\u4e00-\u9fa5]{1,10}$', message: '部门名称格式错误' }]
			}
		};
	},
	methods: {
		loadDataList: function() {
			let that = this;
			that.dataListLoading = true;
			let data = {
				deptName: that.dataForm.deptName,
				page: that.page,
				length: that.rows
			};

			that.$http('dept/searchDeptByPage', 'POST', data, true, function(resp) {
				// console.log(resp.page);
				let page = resp.page;
				that.dataList = page.list;
				that.totalCount = Number(page.totalCount);
				that.dataListLoading = false;
			});
		},
		searchHandle: function() {
			this.$refs['dataForm'].validate(valid => {
				if (valid) {
					this.$refs['dataForm'].clearValidate();
					if (this.dataForm.deptName == '') {
						this.dataForm.deptName = null;
					}
					if (this.page != 1) {
						this.page = 1;
					}
					this.loadDataList();
				} else {
					return false;
				}
			});
		},
		selectable: function(row, index) {
			if (row.emps > 0) {
				return false;
			}
			return true;
		},
		selectionChangeHandle: function(val) {
			this.dataListSelections = val;
		},
		sizeChangeHandle: function(val) {
			this.rows = val;
			this.page = 1;
			this.loadDataList();
		},
		currentChangeHandle: function(val) {
			this.page = val;
			this.loadDataList();
		},
		deleteHandle: function(id) {
			let that = this;
			let ids = id
				? [id]
				: that.dataListSelections.map(item => {
						return item.id;
				  });
			if (ids.length == 0) {
				that.$message({
					message: '没有选中记录',
					type: 'warning',
					duration: 1200
				});
			} else {
				that.$confirm(`确定要删除选中的记录？`, '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					that.$http('dept/deleteDeptByIds', 'POST', { ids: ids }, true, function(resp) {
						if (resp.rows > 0) {
							that.$message({
								message: '操作成功',
								type: 'success',
								duration: 1200,
								onClose: () => {
									that.loadDataList();
								}
							});
						} else {
							that.$message({
								message: '未能删除记录',
								type: 'warning',
								duration: 1200
							});
						}
					});
				});
			}
		},
		addHandle: function() {
			this.$nextTick(() => {
				this.$refs.addOrUpdate.init();
			});
		},
		updateHandle: function(id) {
			this.$nextTick(() => {
				this.$refs.addOrUpdate.init(id);
			});
		}
	},
	created: function() {
		this.loadDataList();
	}
};
</script>

<style></style>
