package cn.aikuiba.result;

import cn.aikuiba.constants.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JSONResult<T> {

    //结果状态
    private boolean success = true;
    //响应码
    private int code = ErrorCode.OK.getCode();
    //默认消息
    private String message = ErrorCode.OK.getMessage();
    //返回的数据
    private T data;

    /**
     * 创建无参数的成功的JSON结果对象
     *
     * @return JSONResult:JSON结果对象
     */
    public static <T> JSONResult<T> success() {
        return new JSONResult<>();
    }

    /**
     * 创建成功的JSON结果对象
     *
     * @param data: 数据
     * @return JSONResult:JSON结果对象
     */
    public static <T> JSONResult<T> success(T data) {
        JSONResult<T> jsonResult = new JSONResult<>();
        jsonResult.setData(data);
        return jsonResult;
    }

    /**
     * 创建成功的JSON结果对象
     *
     * @param code: 响应码
     * @param data: 数据
     * @return JSONResult:JSON结果对象
     */
    public static <T> JSONResult<T> success(int code, T data) {
        JSONResult<T> jsonResult = success(data);
        jsonResult.setCode(code);
        return jsonResult;
    }

    /**
     * 创建失败的JSON结果对象
     *
     * @param code:    响应码
     * @param message: 错误信息
     * @return JSONResult:JSON结果对象
     */
    public static <T> JSONResult<T> error(int code, String message) {
        return new JSONResult<>(false, code, message, null);
    }

    /**
     * 创建失败的JSON结果对象
     *
     * @return JSONResult:JSON结果对象
     */
    public static <T> JSONResult<T> error() {
        return error(ErrorCode.SERVICE_ERROR.getCode(), ErrorCode.SERVICE_ERROR.getMessage());
    }

    /**
     * 创建失败的JSON结果对象
     *
     * @return JSONResult:JSON结果对象
     */
    public static <T> JSONResult<T> error(String message) {
        return error(ErrorCode.SERVICE_ERROR.getCode(), message);
    }

    //直接传递一个错误码
    public static <T> JSONResult<T> error(ErrorCode errorCode) {
        return error(errorCode.getCode(), errorCode.getMessage());
    }

    public JSONResult<T> data(T data) {
        this.data = data;
        return this;
    }
}
