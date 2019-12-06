<template>
  <a-modal
    :title="title"
    :width="1200"
    :visible="visible"
    :maskClosable="false"
    :confirmLoading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel">
    <a-spin :spinning="confirmLoading">
      <!-- 主表单区域 -->
      <a-form :form="form">
        <a-row>

          <a-col :span="12">
            <a-form-item label="等级编号" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="[ 'levelId', validatorRules.levelId]" placeholder="请输入等级编号" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="等级名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="[ 'levelname', validatorRules.levelname]" placeholder="请输入等级名称"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="等级限制" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="[ 'levellimit', validatorRules.levellimit]" placeholder="请输入等级限制" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="分销等级" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-dict-select-tag type="list" v-decorator="['distributionlevel']" :trigger-change="true" dictCode="bc_distributor_level" placeholder="请选择分销等级"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="分享限制" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="[ 'sharelimit', validatorRules.sharelimit]" placeholder="请输入分享限制" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="是否当期消费" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-dict-select-tag type="radio" v-decorator="['iscurrentconsumption']" :trigger-change="true" dictCode="bc_is_current" placeholder="请选择是否当期消费"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="生日特权" :labelCol="labelCol2" :wrapperCol="wrapperCol2">
              <a-textarea v-decorator="['birthdaydesc']" rows="4" placeholder="请输入生日特权"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="专车特权" :labelCol="labelCol2" :wrapperCol="wrapperCol2">
              <a-textarea v-decorator="['cardesc']" rows="4" placeholder="请输入专车特权"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="项目特权" :labelCol="labelCol2" :wrapperCol="wrapperCol2">
              <a-textarea v-decorator="['projectdesc']" rows="4" placeholder="请输入项目特权"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="分享特权" :labelCol="labelCol2" :wrapperCol="wrapperCol2">
              <a-textarea v-decorator="['sharedesc']" rows="4" placeholder="请输入分享特权"/>
            </a-form-item>
          </a-col>

        </a-row>
      </a-form>

      <!-- 子表单区域 -->
      <a-tabs v-model="activeKey" @change="handleChangeTabs">
        <a-tab-pane tab="等级卡券" :key="refKeys[0]" :forceRender="true">
          <j-editable-table
            :ref="refKeys[0]"
            :loading="bcLevelCouponTable.loading"
            :columns="bcLevelCouponTable.columns"
            :dataSource="bcLevelCouponTable.dataSource"
            :maxHeight="300"
            :rowNumber="true"
            :rowSelection="true"
            :actionButton="true"/>
        </a-tab-pane>
        
        <a-tab-pane tab="等级特权项目" :key="refKeys[1]" :forceRender="true">
          <j-editable-table
            :ref="refKeys[1]"
            :loading="bcLevelProjectTable.loading"
            :columns="bcLevelProjectTable.columns"
            :dataSource="bcLevelProjectTable.dataSource"
            :maxHeight="300"
            :rowNumber="true"
            :rowSelection="true"
            :actionButton="true"/>
        </a-tab-pane>
        
      </a-tabs>

    </a-spin>
  </a-modal>
</template>

<script>

  import pick from 'lodash.pick'
  import { FormTypes,getRefPromise } from '@/utils/JEditableTableUtil'
  import { JEditableTableMixin } from '@/mixins/JEditableTableMixin'
  import JDictSelectTag from "@/components/dict/JDictSelectTag"

  export default {
    name: 'BcPatientLevelModal',
    mixins: [JEditableTableMixin],
    components: {
      JDictSelectTag,
    },
    data() {
      return {
        labelCol: {
          span: 6
        },
        wrapperCol: {
          span: 16
        },
        labelCol2: {
          span: 3
        },
        wrapperCol2: {
          span: 20
        },
        // 新增时子表默认添加几行空数据
        addDefaultRowNum: 1,
        validatorRules: {
          levelId: { rules: [{ required: true, message: '请输入等级编号!' }] },
          levelname: { rules: [{ required: true, message: '请输入等级名称!' }] },
          levellimit: { rules: [{ required: true, message: '请输入等级限制!' }] },
          distributionlevel: { rules: [{ required: true, message: '请输入分销等级!' }] },
          sharelimit: { rules: [{ required: true, message: '请输入分享限制!' }] },
          iscurrentconsumption: { rules: [{ required: true, message: '请输入是否当期消费!' }] },
          birthdaydesc: { rules: [{ required: true, message: '请输入生日特权!' }] },
          cardesc: { rules: [{ required: true, message: '请输入专车特权!' }] },
          projectdesc: { rules: [{ required: true, message: '请输入项目特权!' }] },
          sharedesc: { rules: [{ required: true, message: '请输入分享特权!' }] },
        },
        refKeys: ['bcLevelCoupon', 'bcLevelProject', ],
        tableKeys:['bcLevelCoupon', 'bcLevelProject', ],
        activeKey: 'bcLevelCoupon',
        // 等级卡券
        bcLevelCouponTable: {
          loading: false,
          dataSource: [],
          columns: [
            {
              title: '卡券编号',
              key: 'couponid',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue: '',
            },
          ]
        },
        // 等级特权项目
        bcLevelProjectTable: {
          loading: false,
          dataSource: [],
          columns: [
            {
              title: '特权名称',
              key: 'privilegename',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue: '',
            },
            {
              title: '特权描述',
              key: 'privilegedesc',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue: '',
            },
            {
              title: '特权类型',
              key: 'type',
              type: FormTypes.select,
              dictCode:"bc_level_project_type",
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue: '',
            },
            {
              title: '创建人',
              key: 'createBy',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue: '',
            },
          ]
        },
        url: {
          add: "/demo/bcPatientLevel/add",
          edit: "/demo/bcPatientLevel/edit",
          bcLevelCoupon: {
            list: '/demo/bcPatientLevel/queryBcLevelCouponByMainId'
          },
          bcLevelProject: {
            list: '/demo/bcPatientLevel/queryBcLevelProjectByMainId'
          },
        }
      }
    },
    methods: {
      getAllTable() {
        let values = this.tableKeys.map(key => getRefPromise(this, key))
        return Promise.all(values)
      },
      /** 调用完edit()方法之后会自动调用此方法 */
      editAfter() {
        let fieldval = pick(this.model,'levelId','levelname','levellimit','distributionlevel','sharelimit','iscurrentconsumptionString','birthdaydesc','cardesc','projectdesc','sharedesc')
        this.$nextTick(() => {
          this.form.setFieldsValue(fieldval)
        })
        // 加载子表数据
        if (this.model.id) {
          let params = { id: this.model.id }
          this.requestSubTableData(this.url.bcLevelCoupon.list, params, this.bcLevelCouponTable)
          this.requestSubTableData(this.url.bcLevelProject.list, params, this.bcLevelProjectTable)
        }
      },
      /** 整理成formData */
      classifyIntoFormData(allValues) {
        let main = Object.assign(this.model, allValues.formValue)

        return {
          ...main, // 展开
          bcLevelCouponList: allValues.tablesValue[0].values,
          bcLevelProjectList: allValues.tablesValue[1].values,
        }
      },
      validateError(msg){
        this.$message.error(msg)
      },
     popupCallback(row){
       this.form.setFieldsValue(pick(row,'levelId','levelname','levellimit','distributionlevel','sharelimit','iscurrentconsumptionString','birthdaydesc','cardesc','projectdesc','sharedesc'))
     },

    }
  }
</script>

<style scoped>
</style>