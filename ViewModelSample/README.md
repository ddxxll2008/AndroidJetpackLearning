  ViewModel是一个负责准备和管理Activity或Fragment数据的类。它还处理Activity / Fragment与应用程序其余部分的通信（例如，调用业务逻辑类）。

  始终与范围（Fragment或Activity）关联创建ViewModel，并且只要范围处于活动状态，将保留ViewModel。例如。如果它是一个Activity，直到它完成。

  换句话说，这意味着如果ViewModel的所有者因配置更改（例如旋转）而被销毁，则不会销毁它。所有者的新实例将重新连接到现有的ViewModel。

  ViewModel的目的是获取并保留Activity或Fragment所需的信息。 Activity或Fragment应该能够观察ViewModel中的更改。 ViewModels通常通过LiveData或Android数据绑定公开此信息。您还可以使用您最喜欢的框架中的任何可观察性构造。

  ViewModel唯一的职责是管理UI的数据。它永远不应访问您的视图层次结构或将引用保存回活动或片段。

从“活动”角度来看，典型用法是：

```
public class UserActivity extends Activity {

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.user_activity_layout);
         final UserModel viewModel = ViewModelProviders.of(this).get(UserModel.class);
         viewModel.userLiveData.observer(this, new Observer() {
            @Override
             public void onChanged(@Nullable User data) {
                 // update ui.
             }
         });
         findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                  viewModel.doAction();
             }
         });
     }
 }
 ```
 
ViewModel would be:

```
 public class UserModel extends ViewModel {
     public final LiveData<User> userLiveData = new LiveData<>();

     public UserModel() {
         // trigger user load.
     }

     void doAction() {
         // depending on the action, do necessary business logic calls and update the
         // userLiveData.
     }
 }
 ```
 
 ViewModels还可以用作活动的不同片段之间的通信层。 每个Fragment都可以通过其Activity使用相同的密钥获取ViewModel。 这允许以解耦方式在片段之间进行通信，使得它们永远不需要直接与其他片段对话。

```
 public class MyFragment extends Fragment {
     public void onStart() {
         UserModel userModel = ViewModelProviders.of(getActivity()).get(UserModel.class);
     }
 }
 ```
 
 ![image](https://github.com/ddxxll2008/AndroidJetpackLearning/blob/master/ViewModelSample/gif/ViewModelSample.gif)