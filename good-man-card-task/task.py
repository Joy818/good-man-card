from time import sleep


class TaskExecuter:
  def __init__(self, task) -> None:
      self.task = task
      self.success = False
      self.cuase = ''
      self.result = ''

  def execute(self):
    params = self.task.get_dict_params()
    print(f'开始执行任务 {params}')
    sleep(10)
    print(f'任务执行结束 {params}')
    self.success = True

  def is_success(self):
    return self.success


  def get_result(self):
    return self.result

  def get_cause(self):
    return self.cuase