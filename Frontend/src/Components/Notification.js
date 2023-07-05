import { toast } from "react-toastify";

const useNotification = () => {
  const notify = (message, type) => {
    switch (type) {
      case "info":
        toast.info(message);
        break;
      case "success":
        toast.success(message);
        break;
      case "warning":
        toast.warn(message);
        break;
      case "error":
        toast.error(message);
        break;
      default:
        toast(message);
    }
  };

  return { notify };
};
export default useNotification;
