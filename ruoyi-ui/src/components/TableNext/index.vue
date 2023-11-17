<script>
export default {
  props: {
    loading: {
      type: Boolean,
      default: false,
      required: false,
    },
    list: {
      type: Array,
      default: () => [],
    },
    columns: {
      type: Array,
      default: () => [],
    },
    extraOption: {
      type: Object,
      default: () => {
      },
      required: false,
    },
    extraEvent: {
      type: Object,
      default: () => {
      },
      required: false,
    },
    paginateOption: {
      type: Object,
      default: () => {
      },
      required: false,
    },
    paginateEvent: {
      type: Object,
      default: () => {
      },
      required: false,
    },
    customerEmpty: {
      type: String | Object,
      required: false
    }
  },
  render() {

    return (
        <div class="table-next">
          <el-table
              data={this.list}
              v-loading={this.loading}
              element-loading-text='拼命加载中...'
              element-loading-spinner="el-icon-loading"
              elemnt-loading-background="rgba(0,0,0,0.5)"
              props={{...this.extraOption}}
              on={{...this.extraEvent}}>
            // 自定义empty
            {
              this.customerEmpty ?
                  <template slot="empty">{this.customerEmpty}</template>
                  : <template slot="empty">
                    <el-empty imageSize={100}></el-empty>
                  </template>
            }
            // 列渲染
            {this.columns?.map((item) => {
              return (
                  !item.hide &&
                  (item.type !== 'selection' ? (
                      <el-table-column
                          key={'table_' + item.label}
                          props={{...item, prop: item.field}}
                          align={item.align || 'center'}
                      >
                        {(value) => {
                          if (item?.render) {
                            const vNode = item.render(
                                value.row,
                                value.row[item.field],
                                value
                            );
                            return vNode;
                          }
                          return value.row[item.field];
                        }}
                      </el-table-column>
                  ) : (
                      <el-table-column
                          key={'table_' + item.label}
                          props={{...item}}
                          align={item.align || 'center'}
                      />
                  ))
              );
            }) || null}
          </el-table>
          {this.paginateOption?.total > 0 && (
              <el-pagination
                  layout={this.paginateOption?.layout || 'total, prev, pager, next'}
                  props={{...this.paginateOption}}
                  on={{...this.paginateEvent}}
              />
          )}
        </div>
    );
  },
};
</script>

<style lang="scss" scoped>
.table-next {
  ::v-deep .el-table__empty-block {
    width: 100% !important;
    min-width: 100%;
    max-width: 100%;
    position: sticky;
    left: 0;
  }

  ::v-deep .el-pagination {
    display: flex;
    justify-content: flex-end;
    margin-top: 24px;
  }

  ::v-deep .el-table__cell {
    .cell {
      display: flex;
    }
  }
}
</style>
